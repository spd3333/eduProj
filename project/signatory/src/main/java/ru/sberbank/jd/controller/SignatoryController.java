package ru.sberbank.jd.controller;

import java.util.Collection;
import java.util.List;
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
import ru.sberbank.jd.controller.input.SignatoryInput;
import ru.sberbank.jd.entity.Employee;
import ru.sberbank.jd.model.Signatory;
import ru.sberbank.jd.repository.EmployeeRepository;
import ru.sberbank.jd.service.SignatoryService;

/**
 * Контроллер REST запросов микросервиса Подписанты.
 * GET 	    /signatories        - получить все записи(READ)
 * POST	    /signatories	    - создать новую запись(CREATE)
 * GET 	    /signatories/{id}   - получить одну запись(READ)
 * POST 	/signatories/{id}	- Обновить одну запись(UPDATE)
 * DELETE 	/signatories/{id}	- Удалить одну запись(DELETE)
 */
@RestController
@RequestMapping("/signatories")
@RequiredArgsConstructor
@Slf4j
public class SignatoryController {

    private final SignatoryService signatoryService;

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Signatory delId(@PathVariable("id") String id) {

        log.info("[SignatoryService.delId] id = {}", id);
        Signatory signatoryDel = signatoryService.delSignatory(id);

        if (signatoryDel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND Signatory for delete, id = " + id);
        }
        return signatoryDel;
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Signatory updateId(@PathVariable("id") String id,
            @RequestBody SignatoryInput signatoryInput) {

        log.info("[SignatoryService.updateId] id = {}", id);
        Signatory signatoryUpdate = signatoryService.updateId(id, signatoryInput );
        if (signatoryUpdate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND Signatory, id = " + id);
        }
        return signatoryUpdate;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Signatory getId(@PathVariable("id") String id) {

        log.info("[SignatoryService.getId] id = {}", id);
        Signatory signatoryFind = signatoryService.getSignatory(id);

        if (signatoryFind == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND Signatory, id = " + id);
        }

        return signatoryFind;
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Collection<Signatory> getAll() {

        log.info("[Start] GET controller .getAll()");
        return signatoryService.getAll();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Signatory createSignatory(@RequestBody SignatoryInput signatoryInput) {

        log.info("[createSignatory] signatoryInput = {}", signatoryInput);
        Signatory signatoryNew = signatoryService.createSignatory(signatoryInput);
        return signatoryNew;
    }
}