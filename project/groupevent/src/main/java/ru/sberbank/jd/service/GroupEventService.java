package ru.sberbank.jd.service;

import static ru.sberbank.jd.model.EventGroup.ULOGIN;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import ru.sberbank.jd.controller.input.GroupEventInput;
import ru.sberbank.jd.controller.input.SignatoryInput;
import ru.sberbank.jd.entity.GroupEvent;
import ru.sberbank.jd.model.EventGroup;
import ru.sberbank.jd.repository.GroupEventRepository;

/**
 * Бизнес обработка Групповых мероприятий.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GroupEventService {

    private final GroupEventRepository groupEventRepository;
    private final RestTemplate restTemplate;

    public EventGroup createGroupEvent(@NonNull GroupEventInput groupEventInput) {

        //Реестр ведется на уровне БД
        log.info("[CHECK] in service signature, signerId = {}", groupEventInput.signerId());

        //проверка в сервисе Signatory
        //String sign = "4099";
        String sign = getPernr(groupEventInput.signerId());
        if (sign == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND signatore, id = " + groupEventInput.signerId());
        }

        log.info("[createGroupEvent.Service]groupEventInput = {}", groupEventInput);
        GroupEvent groupEventNew = new GroupEvent();
        groupEventNew.setId(UUID.randomUUID().toString());
        groupEventNew.setCode(groupEventInput.code());
        groupEventNew.setType(groupEventInput.type());
        groupEventNew.setName(groupEventInput.name());
        groupEventNew.setInfo(groupEventInput.info());
        groupEventNew.setCountry(groupEventInput.country());
        groupEventNew.setCity(groupEventInput.city());
        groupEventNew.setRegion(groupEventInput.region());
        groupEventNew.setSbt(groupEventInput.sbt());
        groupEventNew.setDeadline(groupEventInput.deadline());
        groupEventNew.setSignerId(groupEventInput.signerId());
        groupEventNew.setActiv(groupEventInput.activ());
        groupEventNew.setOrganizer(groupEventInput.organizer());
        groupEventNew.setBegda(groupEventInput.begda());
        groupEventNew.setEndda(groupEventInput.endda());
        groupEventNew.setUname(ULOGIN);
        groupEventNew.setCreateDateTime(LocalDateTime.now());
        GroupEvent groupEventStorage = groupEventRepository.save(groupEventNew);
        groupEventRepository.flush();

        EventGroup eventGroupStorage =  EventGroup.create(groupEventStorage);
        eventGroupStorage.setSigner(sign);
        return eventGroupStorage;
    }

    public Collection<EventGroup> getAll() {

        Collection<EventGroup> collectionOut = new ArrayList<>();
        List<GroupEvent> listGroupEvent = groupEventRepository.findAll();
        for (GroupEvent groupEvent :listGroupEvent ) {
            EventGroup eventGroup = EventGroup.create(groupEvent);
            eventGroup.setSigner(getPernr(groupEvent.getSignerId()));
            collectionOut.add(eventGroup);
        }
        return collectionOut;
    }

    public EventGroup getGroupEvent(@NonNull String id) {

        log.info("[Repository.get] id = {}", id);
        if (!groupEventRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND GroupEvent, id = " + id);
        }
        GroupEvent groupEventFind = groupEventRepository.findById(id).get();

        EventGroup eventGroupFind =  EventGroup.create(groupEventFind);
        eventGroupFind.setSigner(getPernr(groupEventFind.getSignerId()));

        return eventGroupFind;
    }

    public EventGroup delGroupEvent(@NonNull String id) {

        log.info("[Repository.delete] id = {}", id);
        if (!groupEventRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND GroupEvent, id = " + id);
        }

        GroupEvent groupEventFind = groupEventRepository.findById(id).get();

        EventGroup eventGroupDel =  EventGroup.create(groupEventFind);
        eventGroupDel.setSigner(getPernr(groupEventFind.getSignerId()));

        groupEventRepository.delete(groupEventFind);
        groupEventRepository.flush();
        return eventGroupDel;
    }

    public EventGroup updateId(@NonNull String id, GroupEventInput groupEventInput) {

        log.info("[Repository.update] id = {}", id);
        log.info("[Repository.update] name = {}", groupEventInput);

        //проверка в сервисе Signatory
        String sign = getPernr(groupEventInput.signerId());
        if (sign == null) {
            log.error("Ошибка вызова сервиса signatore, id = {}",groupEventInput.signerId());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND signatore, id = " + groupEventInput.signerId());
        }

        if (!groupEventRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND GroupEvent, id = " + id);
        }
        GroupEvent groupEventFind = groupEventRepository.findById(id).get();

        groupEventFind.setType(groupEventInput.type());
        groupEventFind.setName(groupEventInput.name());
        groupEventFind.setInfo(groupEventInput.info());
        groupEventFind.setCountry(groupEventInput.country());
        groupEventFind.setCity(groupEventInput.city());
        groupEventFind.setRegion(groupEventInput.region());
        groupEventFind.setSbt(groupEventInput.sbt());
        groupEventFind.setDeadline(groupEventInput.deadline());
        groupEventFind.setActiv(groupEventInput.activ());
        groupEventFind.setOrganizer(groupEventInput.organizer());
        groupEventFind.setCode(groupEventInput.code());
        groupEventFind.setBegda(groupEventInput.begda());
        groupEventFind.setEndda(groupEventInput.endda());
        groupEventFind.setUname(ULOGIN);
        groupEventFind.setUpdateDateTime(LocalDateTime.now());
        groupEventFind.setSignerId(groupEventInput.signerId());

        GroupEvent groupEventUpd = groupEventRepository.save(groupEventFind);
        groupEventRepository.flush();

        EventGroup eventGroupUpd = EventGroup.create(groupEventUpd);
        eventGroupUpd.setSigner(getPernr(groupEventUpd.getSignerId()));

        return eventGroupUpd;
    }

    public String getPernr(String id) {

        String url = "http://localhost:8082/signatories/" + id;

        //restTemplate = new RestTemplate();
        ResponseEntity<String> response;

        HttpHeaders headers = new HttpHeaders();
        //верный логин+пароль
        headers.set("Authorization","Basic dXNlcjpkNWVjZmYwOS1iOWQ3LTQwZmQtYWI5MS1lZTk3YmYxMGRkZmU=");

        //специально - ошибочный пароль, для тестирования
        //headers.set("Authorization","Basic XdXNlcjpkNWVjZmYwOS1iOWQ3LTQwZmQtYWI5MS1lZTk3YmYxMGRkZmU");
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        try {
            //response = restTemplate.getForEntity(url, String.class);
            response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        } catch (RestClientException e) {
            log.error("Ошибка вызова сервиса {} на методе RestTemplate.exchange",url);
            return null;
        }

        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            log.error("Ошибка вызова сервиса, не найден");
            return null;
        } else if (response.getStatusCode() != HttpStatus.OK) {
            log.error("Ошибка вызова сервиса");
            return null;
        }

        ObjectMapper mapper = JsonMapper.builder()
                .findAndAddModules()
                .build();

        try {
            SignatoryInput signatoryInput = mapper.readValue(response.getBody(), SignatoryInput.class);
            return signatoryInput.pernr();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка разбора Json");
        }
    }

    public Collection<SignatoryInput> geAllSignatory() {

        String url = "http://localhost:8082/signatories";

        //restTemplate = new RestTemplate();
        ResponseEntity<String> response;

        HttpHeaders headers = new HttpHeaders();
        //верный логин+пароль
        headers.set("Authorization","Basic dXNlcjpkNWVjZmYwOS1iOWQ3LTQwZmQtYWI5MS1lZTk3YmYxMGRkZmU=");
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        try {
            response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        } catch (RestClientException e) {
            log.error("Ошибка вызова сервиса {} на методе RestTemplate.exchange",url);
            return null;
        }

        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            log.error("Ошибка вызова сервиса, не найден");
            return null;
        } else if (response.getStatusCode() != HttpStatus.OK) {
            log.error("Ошибка вызова сервиса");
            return null;
        }

        ObjectMapper mapper = JsonMapper.builder()
                .findAndAddModules()
                .build();

        try {
            Collection<SignatoryInput> listSignatory = mapper.readerForListOf(SignatoryInput.class).readValue(response.getBody());
            return listSignatory;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}