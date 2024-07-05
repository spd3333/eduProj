package ru.sberbank.jd.security.model;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * Роли.
 *
 */
@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private final String value;

    @Override
    public String getAuthority() {
        return value;
    }
}