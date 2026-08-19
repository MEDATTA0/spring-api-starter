package com.codewithmosh.store.carts.dto.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * CartDto
 */
public record CartDto(
    UUID id,
    List<CartItemDto> items,
    BigDecimal totalPrice
) {}
