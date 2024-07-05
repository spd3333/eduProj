package ru.sberbank.jd.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.sberbank.jd.controller.input.PositionInput;
import ru.sberbank.jd.controller.input.SignatoryInput;
import ru.sberbank.jd.controller.output.PositionOut;
import ru.sberbank.jd.model.Signatory;
import ru.sberbank.jd.security.model.Person;
import ru.sberbank.jd.service.PersonService;
import ru.sberbank.jd.service.PositionService;
import ru.sberbank.jd.service.SignatoryService;

/**
 * Контроллер UI списка пользователей сервиса
 * GET 	    /ui-all-user        - получить всех пользователей сервиса
 * GET 	    /ui-all-position    - получить всех должностей подписантов
 * GET 	    /ui-all-signatory   - получить всех подписантов
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class PersonUiController {

    private final PersonService personService;
    private final PositionService positionService;
    private final SignatoryService signatoryService;

    @GetMapping("/ui-all-user")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ModelAndView allUsers(){

        log.info("[Start] Ui .allUser()");
        ModelAndView modelAndView = new ModelAndView();
        Collection<Person> users = personService.getAll();
        modelAndView.setViewName("all-user");
        modelAndView.addObject("users",users);
        return modelAndView;
    }

    @GetMapping("/ui-all-position")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ModelAndView allPosition(){

        log.info("[Start] Ui .allPosition()");
        ModelAndView modelAndView = new ModelAndView();
        Collection<PositionOut> positions = positionService.getAll();
        modelAndView.setViewName("all-position");
        modelAndView.addObject("positions",positions);
        return modelAndView;
    }

    @GetMapping("/ui-exit")
    public ModelAndView allExit(HttpServletRequest request){

        log.info("[Start] Ui .allExit()");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate();
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("exit");
        return modelAndView;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {

        log.info("[Start] .logout()");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/ui-all-signatory")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ModelAndView allSignatory(){

        log.info("[Start] Ui .allSignatory()");
        ModelAndView modelAndView = new ModelAndView();
        Collection<Signatory> signatories = signatoryService.getAll();
        modelAndView.setViewName("all-signatory");
        modelAndView.addObject("signatories",signatories);
        return modelAndView;
    }

    @GetMapping("/ui-position-new")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String newPosition(@ModelAttribute("position") PositionInput positionInput) {

        log.info("[GET /ui-position-new .newPosition()] positionInput = {}", positionInput);
        return "position_new";
    }

    @PostMapping("/position-create")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String createPosition(@ModelAttribute("position") PositionInput positionInput) {

        log.info("[POST /position-create  .createPosition()] positionInput = {}", positionInput);
        positionService.createPosition(positionInput);
        return "redirect:/ui-all-position";
    }
    @GetMapping("/ui-signatory-new")
    public String newSignature(@ModelAttribute("signatory") SignatoryInput signatoryInput) {

        log.info("[GET /ui-signatory-new .newSignature()] signatoryInput = {}", signatoryInput);
        return "signatory_new";
    }

    @PostMapping("/signatory-create")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String createSignature(@ModelAttribute("signatory") SignatoryInput signatoryInput) {

        log.info("[POST /signatory-create  .createSignatory()] signatoryInput = {}", signatoryInput);
        signatoryService.createSignatory(signatoryInput);
        return "redirect:/ui-all-signatory";
    }
}