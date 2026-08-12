package com.codewithmosh.store.users.dto.response;

import com.codewithmosh.store.users.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

/**
 * DTO for {@link User}
 */
public record UserDto(
    Long id,
    String name,
    String email
) implements Serializable {
    public static UserDto of(User u) {
        return new UserDto(u.getId(), u.getName(), u.getEmail());
    }
}
