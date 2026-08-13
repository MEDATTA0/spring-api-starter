package com.codewithmosh.store.users.dto.request;

import jakarta.validation.constraints.NotBlank;

/**
 * UserChangePassordRequest
 */
public record UserChangePassordRequest(
    @NotBlank String oldPassword,
    @NotBlank String newPassword
) {}
