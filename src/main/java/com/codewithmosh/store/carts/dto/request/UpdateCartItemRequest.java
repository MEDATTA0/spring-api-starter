package com.codewithmosh.store.carts.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * CartItemUpdateRequest
 */
public record UpdateCartItemRequest(
    @NotNull(message = "Quantity must be provided")
    @Min(value = 1, message = "Quantity must be greater than 0")
    @Max(value = 100, message = "Quantity must be less than or equal 100")
    Integer quantity
) {}
