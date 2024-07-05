package ru.sberbank.jd.repository;

import jakarta.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.sberbank.jd.security.model.Person;
import ru.sberbank.jd.security.model.Role;

/**
 * Реализация интерфейса репозитория работы с персонами PersonRepository.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class PersonRepositoryImpl implements PersonRepository {

    private final Map<String, Person> storage = new HashMap<>();

    @Override
    public Person save(Person person) {

        if (person == null) {
            return null;
        }

        storage.put(person.getLogin(), person);
        log.info("Save new person in Repository, result = {}", person);
        return person;
    }

    @Override
    public Person get(String login) {

        return storage.get(login);
    }

    @Override
    public Person delete(String login) {

        return storage.remove(login);
    }

    @Override
    public Collection<Person> list() {

        return storage.values();
    }

    /**
     * Начальные данные для тестирования.
     */
    @PostConstruct
    public void initPersons() {
        Set<String> roles1 = new HashSet<String>();
        roles1.add(Role.ADMIN.getAuthority());
        roles1.add(Role.USER.getAuthority());

        Person person1 = new Person(
                "login1",
                "Ivan",
                "Petrov",
                "2000-01-01",
                "pswd1",
                roles1 );
        save(person1);

        Set<String> roles2 = new HashSet<String>();
        roles2.add(Role.ADMIN.getAuthority());
        Person person2 = new Person(
                "login2",
                "Fedor",
                "Ivanov",
                "2015-02-02",
                "pswd2",
                roles2 );
        save(person2);

        Set<String> roles3 = new HashSet<String>();
        roles3.add(Role.USER.getAuthority());
        Person person3 = new Person(
                "login3",
                "Alex",
                "Fox",
                "2015-02-02",
                "pswd3",
                roles3 );
        save(person3);

        Person person5 = new Person(
                "user",
                "Sergey",
                "Dyakov",
                "1976-02-16",
                "d5ecff09-b9d7-40fd-ab91-ee97bf10ddfe",
                roles2 );
        save(person5);
    }
    }