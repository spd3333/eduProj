package ru.sberbank.jd.service;

import static ru.sberbank.jd.model.Signatory.ULOGIN;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.sberbank.jd.controller.input.SignatoryInput;
import ru.sberbank.jd.entity.Employee;
import ru.sberbank.jd.entity.Position;
import ru.sberbank.jd.model.Signatory;
import ru.sberbank.jd.repository.EmployeeRepository;
import ru.sberbank.jd.repository.PositionRepository;

/**
 * Обработка логики Подписантов.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SignatoryService {

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;

    public Signatory createSignatory(@NonNull SignatoryInput signatoryInput) {

        //Реестр ведется на уровне БД
        log.info("[createSignatory.Service]");

        log.info("[RepositoryPosition.get] id = {}", signatoryInput.position_id());
        if (!positionRepository.existsById(signatoryInput.position_id())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND Position, id = " + signatoryInput.position_id());
        }
        Position positionFind = positionRepository.findById(signatoryInput.position_id()).get();

        Employee employeeNew = new Employee();
        employeeNew.setId(UUID.randomUUID().toString());
        employeeNew.setPernr(signatoryInput.pernr());
        employeeNew.setSname(signatoryInput.sname());
        employeeNew.setEname(signatoryInput.ename());
        employeeNew.setPname(signatoryInput.pname());
        employeeNew.setBirthDay(signatoryInput.birthDay());
        employeeNew.setOrgeh(signatoryInput.orgeh());
        employeeNew.setBukrs(signatoryInput.bukrs());
        employeeNew.setWerks(signatoryInput.werks());
        employeeNew.setPersk(signatoryInput.persk());
        employeeNew.setKostl(signatoryInput.kostl());
        employeeNew.setUname(ULOGIN);
        employeeNew.setHisto(signatoryInput.histo());
        employeeNew.setBegda(signatoryInput.begda());
        employeeNew.setEndda(signatoryInput.endda());
        employeeNew.setCreateDateTime(LocalDateTime.now());
        employeeNew.setPosition(positionFind);

        Employee employeeStorage = employeeRepository.save(employeeNew);
        employeeRepository.flush();

        return Signatory.create(employeeStorage);
    }

    public Collection<Signatory> getAll() {

        Collection<Signatory> collectionOut = new ArrayList<Signatory>();
        List<Employee> listEmployee = employeeRepository.findAll();
        for (Employee employee :listEmployee ) {
            collectionOut.add(Signatory.create(employee));
        }
        return collectionOut;
    }

    public Signatory getSignatory(@NonNull String id) {

        log.info("[Repository.get] id = {}", id);
        if (!employeeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND Signatory, id = " + id);
        }
        Employee employeeFind = employeeRepository.findById(id).get();
        return Signatory.create(employeeFind);
    }

    public Signatory delSignatory(@NonNull String id) {

        log.info("[Repository.delete] id = {}", id);

        if (!employeeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND Signatory, id = " + id);
        }

        Employee employeeFind = employeeRepository.findById(id).get();
        Signatory signatoryDel =  Signatory.create(employeeFind);

        employeeRepository.delete(employeeFind);
        employeeRepository.flush();
        return signatoryDel;
    }

    public Signatory updateId(@NonNull String id, SignatoryInput signatoryInput) {

        log.info("[Repository.update] id = {}", id);
        log.info("[Repository.update] name = {}", signatoryInput);

        if (!employeeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND Signatory, id = " + id);
        }
        Employee employeeFind = employeeRepository.findById(id).get();

        if (!positionRepository.existsById(signatoryInput.position_id())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND Position, id = " + signatoryInput.position_id());
        }
        Position positionFind = positionRepository.findById(signatoryInput.position_id()).get();

        employeeFind.setPernr(signatoryInput.pernr());
        employeeFind.setSname(signatoryInput.sname());
        employeeFind.setEname(signatoryInput.ename());
        employeeFind.setPname(signatoryInput.pname());
        employeeFind.setBirthDay(signatoryInput.birthDay());
        employeeFind.setOrgeh(signatoryInput.orgeh());
        employeeFind.setPosition(positionFind);
        employeeFind.setBukrs(signatoryInput.bukrs());
        employeeFind.setWerks(signatoryInput.werks());
        employeeFind.setPersk(signatoryInput.persk());
        employeeFind.setKostl(signatoryInput.kostl());
        employeeFind.setUname(ULOGIN);
        employeeFind.setHisto(signatoryInput.histo());
        employeeFind.setBegda(signatoryInput.begda());
        employeeFind.setEndda(signatoryInput.endda());
        employeeFind.setUpdateDateTime(LocalDateTime.now());

        Employee employeeUpd = employeeRepository.save(employeeFind);
        employeeRepository.flush();
        return Signatory.create(employeeUpd);
    }
}