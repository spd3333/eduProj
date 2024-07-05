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
import ru.sberbank.jd.security.model.Person;
import ru.sberbank.jd.service.PersonService;

/**
 * Контроллер REST запросов списка пользователей /persons
 * GET 	    /persons         		- получить все записи(READ)
 * POST	    /persons		        - создать новую запись(CREATE)
 * GET 	    /persons/{login}	    - получить одного пользователя(READ)
 * DELETE 	/persons/{login}	    - Удалить пользователя(DELETE)
 * GET      /persons/no_authorize   - получить все записи без авторизации
 */
@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
@Slf4j
public class PersonController {

    private final PersonService personService;

    /**
     * Прочитать информацию по пользователю.
     *
     * @param login - login пользователя
     * @return - объект Person
     */
    @GetMapping("/{login}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Person getPerson(@PathVariable("login") String login) {

        return personService.getPerson(login);
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Collection<Person> getPersons() {

        return personService.getAll();
    }

    /**
     * Создать пользователя.
     *
     * @param newPerson - новый пользователь
     * @return - пользователь.
     */
    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public Person createPerson(@RequestBody Person newPerson) {
        return personService.createPerson(newPerson);
    }

    /**
     * Удалить пользователя из системы по логину.
     *
     * @param login - пользователь
     * @return - вернуть удаленного пользователя
     */
    @DeleteMapping("/{login}")
    @PreAuthorize("hasRole('ADMIN')")
    public Person delPerson(@PathVariable("login") String login) {

        log.debug("[PersonService.delPerson] login = {}", login);
        Person personDel = personService.delLogin(login);
        if (personDel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND login for delete : " + login);
        }
        return personDel;
    }

    /**
     * Список пользователей без авторизации.
     * Необходим для тестирования начальных данных
     *
     * @return - список объектов Person
     */
    @GetMapping("/no_authorize")
    public Collection<Person> initPersons() {

        return personService.getAll();
    }
}