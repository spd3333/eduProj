package ru.sberbank.jd.controller;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.sberbank.jd.controller.input.GroupEventInput;
import ru.sberbank.jd.model.EventGroup;
import ru.sberbank.jd.service.GroupEventService;

/**
 * Контроллер REST запросов Мероприятий (GroupEvent) .
 * GET 	    /group-events        - получить все записи(READ)
 * POST	    /group-events	     - создать новую запись(CREATE)
 * GET 	    /group-events/{id}   - получить одну запись(READ)
 * POST 	/group-events/{id}	 - Обновить одну запись(UPDATE)
 * DELETE 	/group-events/{id}	 - Удалить одну запись(DELETE)
 */
@RestController
@RequestMapping("/group-events")
@RequiredArgsConstructor
@Slf4j
public class GroupEventController {

    private final GroupEventService groupEventService;

    @DeleteMapping("/{id}")
    public EventGroup delId(@PathVariable("id") String id) {

        log.info("[SignatoryService.delId] id = {}", id);
        EventGroup eventGroupDel = groupEventService.delGroupEvent(id);

        if (eventGroupDel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND GroupEvent for delete, id = " + id);
        }
        return eventGroupDel;
    }

    @PostMapping("/{id}")
    public EventGroup updateId(@PathVariable("id") String id,
                               @RequestBody GroupEventInput groupEventInput) {

        log.info("[SignatoryService.updateId] id = {}", id);
        EventGroup eventGroupUpdate = groupEventService.updateId(id, groupEventInput );
        if (eventGroupUpdate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND GroupEvent, id = " + id);
        }
        return eventGroupUpdate;
    }

    @GetMapping("/{id}")
    public EventGroup getId(@PathVariable("id") String id) {

        log.info("[EventGroupService.getId] id = {}", id);
        EventGroup eventGroupFind = groupEventService.getGroupEvent(id);
        if (eventGroupFind == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND GroupEvent, id = " + id);
        }
        return eventGroupFind;
    }

    @GetMapping()
    public Collection<EventGroup> getAll() {

        log.info("[Start] GET controller .getAll()");
        return groupEventService.getAll();
    }

    @PostMapping(value = "/")
    public EventGroup createOneGroupEvent(@RequestBody GroupEventInput groupEventInput) {

        log.info("[createSignatory] groupEventInput = {}", groupEventInput);
        EventGroup eventGroupNew = groupEventService.createGroupEvent(groupEventInput);
        return eventGroupNew;
    }
}