package ru.sberbank.jd.controller;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.sberbank.jd.controller.input.PositionInput;
import ru.sberbank.jd.controller.output.PositionOut;
import ru.sberbank.jd.service.PositionService;

/**
 * Контроллер REST запросов Должность (Position) .
 * GET 	    /positions        - получить все записи(READ)
 * POST	    /positions	      - создать новую запись(CREATE)
 * GET 	    /positions/{id}   - получить одну запись(READ)
 * POST 	/positions/{id}	  - Обновить одну запись(UPDATE)
 * DELETE 	/positions/{id}	  - Удалить одну запись(DELETE)
 */
@RestController
@RequestMapping("/positions")
@RequiredArgsConstructor
@Slf4j
public class PositionController {
    private final PositionService positionService;

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public PositionOut delId(@PathVariable("id") String id) {

        log.info("[SignatoryService.delId] id = {}", id);
        PositionOut PositionOutDel = positionService.delPosition(id);

        if (PositionOutDel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND Position for delete, id = " + id);
        }
        return PositionOutDel;
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public PositionOut updateId(@PathVariable("id") String id,
            @RequestBody PositionInput positionInput) {

        log.info("[SignatoryService.updateId] id = {}", id);
        PositionOut positionUpdate = positionService.updateId(id, positionInput );
        if (positionUpdate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND Position, id = " + id);
        }
        return positionUpdate;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public PositionOut getId(@PathVariable("id") String id) {

        log.info("[PositionService.getId] id = {}", id);
        PositionOut positionOutFind = positionService.getPosition(id);

        if (positionOutFind == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND Position, id = " + id);
        }

        return positionOutFind;
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Collection<PositionOut> getAll() {

        log.info("[Start] GET PositionController .getAll()");
        return positionService.getAll();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public PositionOut createPosition(@RequestBody PositionInput positionInput) {

        log.info("[createPosition()] positionInput = {}", positionInput);
        PositionOut positionOutNew = positionService.createPosition(positionInput);
        return positionOutNew;
    }
}