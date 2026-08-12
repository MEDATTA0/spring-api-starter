package com.codewithmosh.store.users.dto.request;

import lombok.Data;

/**
 * UpdateUserRequest
 */
@Data
public class UserUpdateRequest {

    private String name;
    private String password;
}
