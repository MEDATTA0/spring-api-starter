package com.codewithmosh.store.dtos;

import com.codewithmosh.store.entities.Product;
import java.math.BigDecimal;

/**
 * ProductDto
 */
public record ProductDto(
    Long id,
    String name,
    String description,
    BigDecimal price,
    Byte categoryId
) {
    public static ProductDto of(Product p) {
        return new ProductDto(
            p.getId(),
            p.getName(),
            p.getDescription(),
            p.getPrice(),
            p.getCategory().getId()
        );
    }
}
