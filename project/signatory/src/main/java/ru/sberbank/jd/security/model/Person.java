package ru.sberbank.jd.security.model;

import java.util.Set;

/**
 * Модель обмена данными Подписант.
 */
public class Person {

    private String login;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String password;
    private Set<String> roles;

    public Person() {
    }

    public Person(String login, String firstName, String lastName, String birthDate, String password, Set<String> roles) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.password = password;
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }
}