package com.codewithmosh.store.products.dto.response;

import com.codewithmosh.store.products.entities.Product;
import java.math.BigDecimal;

/**
 * ProductDto
 */
public record ProductResponse(
    Long id,
    String name,
    String description,
    BigDecimal price,
    Byte categoryId
) {
    public static ProductResponse of(Product p) {
        return new ProductResponse(
            p.getId(),
            p.getName(),
            p.getDescription(),
            p.getPrice(),
            p.getCategory().getId()
        );
    }
}
