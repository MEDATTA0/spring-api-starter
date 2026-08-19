package com.codewithmosh.store.carts.mappers;

import com.codewithmosh.store.carts.dto.response.CartDto;
import com.codewithmosh.store.carts.dto.response.CartItemDto;
import com.codewithmosh.store.carts.entities.Cart;
import com.codewithmosh.store.carts.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * CartMapper
 */
@Mapper(componentModel = "spring")
public interface CartMapper {
    // @Mapping(target = "items", source = "items") // No longer necessary since target and source have the same name
    @Mapping(target = "totalPrice", expression = "java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);

    @Mapping(
        target = "totalPrice",
        expression = "java(item.getTotalPrice())"
    )
    CartItemDto toDto(CartItem item);
}
