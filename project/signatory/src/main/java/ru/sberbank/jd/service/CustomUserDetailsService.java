package ru.sberbank.jd.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.sberbank.jd.repository.PersonRepository;
import ru.sberbank.jd.security.model.Person;

/**
 * Реализация интерфейса UserDetailsService.
 */
@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    /**
     * Вернуть пользователя по логину.
     *
     * @param username - логин
     * @return - объект User
     * @throws UsernameNotFoundException - исключение - пользователь не найден
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.get(username);

        if (person == null) {
            throw new UsernameNotFoundException("Person not found");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String role : person.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return new User(
                person.getLogin(),
                person.getPassword(),
                authorities);
    }
}