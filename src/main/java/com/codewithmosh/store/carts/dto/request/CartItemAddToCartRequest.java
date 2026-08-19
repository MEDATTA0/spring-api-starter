package com.codewithmosh.store.carts.dto.request;

import jakarta.validation.constraints.NotNull;

/**
 * CartItemAddToCartRequest
 */
public record CartItemAddToCartRequest(@NotNull Long productId) {}
