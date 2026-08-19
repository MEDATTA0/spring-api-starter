package com.codewithmosh.store.carts.dto.response;

import com.codewithmosh.store.carts.entities.CartItem;
import com.codewithmosh.store.products.entities.Product;
import java.math.BigDecimal;

/**
 * CartItemDto
 */
public record CartItemDto(
    CartProductDto product,
    int quantity,
    BigDecimal totalPrice
) {
    public static CartItemDto of(CartItem item, Product product) {
        BigDecimal quantity = BigDecimal.valueOf(item.getQuantity());
        var totalPrice = product.getPrice().multiply(quantity);
        CartProductDto productDto = CartProductDto.of(product);
        return new CartItemDto(productDto, item.getQuantity(), totalPrice);
    }
}
