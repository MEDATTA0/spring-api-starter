package com.codewithmosh.store.products.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * CreateProductDto
 */
public record ProductCreateRequest(
    @NotBlank String name,
    String description,
    @Min(0) BigDecimal price,
    Byte categoyId
) {}
