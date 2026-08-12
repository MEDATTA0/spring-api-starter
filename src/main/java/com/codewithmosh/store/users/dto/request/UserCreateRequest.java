package com.codewithmosh.store.users.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * CreateUserDto
 */
@Data
public class UserCreateRequest {

    private String name;

    @Email(message = "email should be a valid email")
    private String email;

    @Length(
        min = 8,
        max = 20,
        message = "Password lenght should be between 8 and 20"
    )
    private String password;
}
