package com.codewithmosh.store.carts;

import com.codewithmosh.store.carts.dto.request.CartItemAddToCartRequest;
import com.codewithmosh.store.carts.dto.request.UpdateCartItemRequest;
import com.codewithmosh.store.carts.dto.response.CartDto;
import com.codewithmosh.store.carts.dto.response.CartItemDto;
import com.codewithmosh.store.carts.entities.Cart;
import com.codewithmosh.store.carts.mappers.CartMapper;
import com.codewithmosh.store.carts.repositories.CartRepository;
import com.codewithmosh.store.core.exceptions.CartItemNotFoundException;
import com.codewithmosh.store.core.exceptions.CartNotFoundException;
import com.codewithmosh.store.core.exceptions.ProductNotFoundException;
import com.codewithmosh.store.products.repositories.ProductRepository;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * CartService
 */
@AllArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;

    public CartDto createCart() {
        var cart = new Cart();
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    public List<CartDto> getAllCarts() {
        return cartRepository
            .findAll()
            .stream()
            .map(cartMapper::toDto)
            .toList();
    }

    public CartItemDto addToCart(
        UUID cartId,
        CartItemAddToCartRequest request
    ) {
        var cart = cartRepository.getCarWithItems(cartId).orElse(null);
        if (cart == null) throw new CartNotFoundException(cartId.toString());
        var product = productRepository
            .findById(request.productId())
            .orElse(null);
        if (product == null) throw new ProductNotFoundException(
            request.productId().toString()
        );

        var cartItem = cart.addItem(product);

        cartRepository.save(cart);

        return cartMapper.toDto(cartItem);
    }

    public CartDto getCart(UUID cartId) {
        var cart = cartRepository.getCarWithItems(cartId).orElse(null);
        if (cart == null) throw new CartNotFoundException(cartId.toString());

        return cartMapper.toDto(cart);
    }

    public CartItemDto updateItem(
        UUID cartId,
        Long productId,
        UpdateCartItemRequest request
    ) {
        var cart = cartRepository.getCarWithItems(cartId).orElse(null);
        if (cart == null) throw new CartNotFoundException(cartId.toString());

        var cartItem = cart.getItem(productId);
        if (cartItem == null) throw new CartItemNotFoundException(
            productId.toString()
        );

        cartItem.setQuantity(request.quantity());
        cartRepository.save(cart);

        return cartMapper.toDto(cartItem);
    }

    public void removeItem(UUID cartId, Long productId) {
        var cart = cartRepository.getCarWithItems(cartId).orElse(null);
        if (cart == null) throw new CartNotFoundException(cartId.toString());

        cart.removeItem(productId);
        cartRepository.save(cart);
    }

    public void clearCart(UUID cartId) {
        var cart = cartRepository.getCarWithItems(cartId).orElse(null);
        if (cart == null) throw new CartNotFoundException(cartId.toString());
        cart.clear();
        cartRepository.save(cart);
    }
}
