package ru.sberbank.jd.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import ru.sberbank.jd.controller.input.GroupEventInput;
import ru.sberbank.jd.controller.input.SignatoryInput;
import ru.sberbank.jd.model.EventGroup;
import ru.sberbank.jd.service.GroupEventService;

/**
 * Контроллер UI списка групповых мероприятий
 * GET 	/ui-all-group-event	- получить список всех мероприятий(READ)
 */
@Controller
@RequiredArgsConstructor
@Slf4j

public class GroupEventUiController {

    private final GroupEventService groupEventService;

    @GetMapping("/ui-all-group-event")
    public ModelAndView allGroupEvent(){

        log.info("[Start] Ui .allGroupEvent()");
        ModelAndView modelAndView = new ModelAndView();
        Collection<EventGroup> events = groupEventService.getAll();
        modelAndView.setViewName("all-event");
        modelAndView.addObject("events",events);
        return modelAndView;
    }
    @GetMapping("/ui-exit")
    public ModelAndView allExit(){

        log.info("[Start] Ui .allExit()");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("exit");
        return modelAndView;
    }

    @GetMapping("/logout")
    public String logout() {

        log.info("[Start] .logout()");
        return "redirect:/";
    }

    @GetMapping("/ui-group-event-new")
    public String newGroupEvent(@ModelAttribute("event") GroupEventInput groupEventInput) {

        log.info("[GET /ui-group-event-new .newGroupEvent()] groupEventInput = {}", groupEventInput);
        return "event_new";
    }

    @PostMapping("/group-event-create")
    public String createGroupEvent(@ModelAttribute("event") GroupEventInput groupEventInput) {

        log.info("[POST /group-event-create .createGroupEvent()] groupEventInput = {}", groupEventInput);
        try {
            groupEventService.createGroupEvent(groupEventInput);
            return "redirect:/ui-all-group-event";
        } catch (ResponseStatusException e) {
            return "/event_error";
        }
    }

    @GetMapping("/ui-all-sygnatory")
    public ModelAndView allSygnatory(){

        log.info("[Get] Ui .allSygnatory()");
        ModelAndView modelAndView = new ModelAndView();
        Collection<SignatoryInput> signatories = groupEventService.geAllSignatory();
        modelAndView.setViewName("all-signatory-sm");
        modelAndView.addObject("signatories",signatories);
        return modelAndView;
    }
}