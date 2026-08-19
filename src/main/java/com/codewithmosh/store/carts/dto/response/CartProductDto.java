package com.codewithmosh.store.carts.dto.response;

import com.codewithmosh.store.products.entities.Product;
import java.math.BigDecimal;

/**
 * CartProductDto
 */
public record CartProductDto(Long id, String name, BigDecimal price) {
    public static CartProductDto of(Product product) {
        return new CartProductDto(
            product.getId(),
            product.getName(),
            product.getPrice()
        );
    }
}
