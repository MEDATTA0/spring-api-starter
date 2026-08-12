package com.codewithmosh.store.dtos;

import com.codewithmosh.store.entities.User;

/**
 * UserDto
 */
public record UserDto(Long id, String name, String email) {
    public static UserDto of(User u) {
        return new UserDto(u.getId(), u.getName(), u.getEmail());
    }
}
