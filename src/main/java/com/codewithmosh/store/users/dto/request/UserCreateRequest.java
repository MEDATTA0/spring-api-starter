package com.codewithmosh.store.users.dto.request;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * CreateUserDto
 */
public record UserCreateRequest(
    String name,

    @Email(message = "email should be a valid email") String email,

    @Length(
        min = 8,
        max = 20,
        message = "Password lenght should be between 8 and 20"
    )
    String password
) {}
