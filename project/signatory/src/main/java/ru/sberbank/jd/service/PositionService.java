package ru.sberbank.jd.service;

import static ru.sberbank.jd.model.Signatory.ULOGIN;

import java.time.LocalDate;
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
import ru.sberbank.jd.controller.input.PositionInput;
import ru.sberbank.jd.controller.output.PositionOut;
import ru.sberbank.jd.entity.Position;
import ru.sberbank.jd.repository.PositionRepository;

/**
 * Бизнес обработка логики Должности.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PositionService {
    private final PositionRepository positionRepository;

    public PositionOut createPosition(@NonNull PositionInput positionInput) {

        //Реестр ведется на уровне БД
        log.info("[createPosition.Service]");

        Position positionNew = new Position();
        positionNew.setId(UUID.randomUUID().toString());
        positionNew.setPlans(positionInput.plans());
        positionNew.setName(positionInput.name());
        positionNew.setUname(ULOGIN);
        positionNew.setHisto(positionInput.histo());
        positionNew.setBegda(positionInput.begda());
        positionNew.setEndda(positionInput.endda());
        positionNew.setCreateDateTime(LocalDateTime.now());

        Position positionStorage = positionRepository.save(positionNew);
        positionRepository.flush();
        return PositionOut.create(positionStorage);
    }

    public Collection<PositionOut> getAll() {

        Collection<PositionOut> collectionOut = new ArrayList<>();
        List<Position> listPosition = positionRepository.findAll();
        for (Position position :listPosition ) {
            collectionOut.add(PositionOut.create(position));
        }
        return collectionOut;
    }

    public PositionOut getPosition(@NonNull String id) {

        log.info("[Repository.getPosition] id = {}", id);
        if (!positionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND Position, id = " + id);
        }
        Position positionFind = positionRepository.findById(id).get();
        return PositionOut.create(positionFind);
    }

    public PositionOut delPosition(@NonNull String id) {

        log.info("[PositionRepository.delete] id = {}", id);

        if (!positionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND Position, id = " + id);
        }

        Position positionFind = positionRepository.findById(id).get();
        PositionOut positionOutDel = PositionOut.create(positionFind);
        positionRepository.delete(positionFind);
        positionRepository.flush();

        return positionOutDel;
    }

    public PositionOut updateId(@NonNull String id, PositionInput positionInput) {

        log.info("[PositionRepository.update] id = {}", id);
        log.info("[PositionRepository.update] name = {}", positionInput);

        if (!positionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND Position, id = " + id);
        }
        Position positionFind = positionRepository.findById(id).get();
        positionFind.setPlans(positionInput.plans());
        positionFind.setName(positionInput.name());
        positionFind.setUname(ULOGIN);
        positionFind.setHisto(positionInput.histo());
        positionFind.setBegda(positionInput.begda());
        positionFind.setEndda(positionInput.endda());
        positionFind.setUpdateDateTime(LocalDateTime.now());

        Position positionUpd = positionRepository.save(positionFind);
        positionRepository.flush();
        return PositionOut.create(positionUpd);
    }
}