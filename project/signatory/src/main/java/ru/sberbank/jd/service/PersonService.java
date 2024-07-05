package ru.sberbank.jd.service;

import java.util.Collection;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.repository.PersonRepository;
import ru.sberbank.jd.security.model.Person;

/**
 * Service обработка Person.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person createPerson(@NonNull Person person) {

        log.info("[ PersonService.Repository.save] person = {}", person);
        Person resultPerson = personRepository.save(person);
        return resultPerson;
    }

    public Person getPerson(@NonNull String login) {

        log.info("[Repository.get] login = {}", login);
        Person resultPersonFind = personRepository.get(login);
        return resultPersonFind;
    }

    public Person delLogin(@NonNull String login) {

        log.info("[Repository.delete] login = {}", login);
        Person resultPersonDelete = personRepository.delete(login);
        return resultPersonDelete;
    }

    public Collection<Person> getAll() {

        return personRepository.list();
    }
}