package ru.sberbank.jd.repository;

import java.util.Collection;
import ru.sberbank.jd.security.model.Person;

/**
 * Интерфейс работы с репозиторием PersonList.
 */
public interface PersonRepository {

    /**
     * Сохранение нового человека в репозиторий.
     *
     * @param  - новый пользователь
     * @return - вернуть новый Person при успешном сохранении
     */
    public Person save(Person person);

    /**
     * Получить персону из Репозитория по логину.
     *
     * @param login - уникальный ключ
     * @return - вернуть человека из Репозитория в формате класса Person
     */
    public Person get(String login);

    /**
     * Удалить человека из репозитория.
     *
     * @param login ключ персоны
     * @return вернуть удаленную персону
     */
    public Person delete(String login);

    /**
     * Получить все записи реестра пользователей.
     *
     * @return - список
     */
    public Collection<Person> list();
}