package com.codewithmosh.store.carts;

import com.codewithmosh.store.carts.dto.request.CartItemAddToCartRequest;
import com.codewithmosh.store.carts.dto.request.UpdateCartItemRequest;
import com.codewithmosh.store.carts.dto.response.CartDto;
import com.codewithmosh.store.carts.dto.response.CartItemDto;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * CartController
 */
@AllArgsConstructor
@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartDto> createCart(UriComponentsBuilder uriBuilder) {
        var cartDto = cartService.createCart();
        var uri = uriBuilder
            .path("/carts/{id}")
            .buildAndExpand(cartDto.id())
            .toUri();

        return ResponseEntity.created(uri).body(cartDto);
    }

    @GetMapping
    public List<CartDto> getAllCarts() {
        var carts = cartService.getAllCarts();
        return carts;
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItemDto> addToCart(
        @PathVariable UUID cartId,
        @Valid @RequestBody CartItemAddToCartRequest request,
        UriComponentsBuilder uriBuilder
    ) {
        var cartItemDto = cartService.addToCart(cartId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);

        // var uri = uriBuilder
        //     .path("/{cartId}/items/{itemId}")
        //     .buildAndExpand(cartId, cartItem.getId())
        //     .toUri();
        // return ResponseEntity.created(uri).body(itemDto);
    }

    @GetMapping("/{cartId}")
    public CartDto getCart(@PathVariable UUID cartId) {
        var cartDto = cartService.getCart(cartId);
        return cartDto;
    }

    @PutMapping("/{cartId}/items/{productId}")
    public CartItemDto updateItem(
        @PathVariable("cartId") UUID cartId,
        @PathVariable("productId") Long productId,
        @Valid @RequestBody UpdateCartItemRequest request
    ) {
        var cartItemDto = cartService.updateItem(cartId, productId, request);
        return cartItemDto;
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> removeItem(
        @PathVariable("cartId") UUID cartId,
        @PathVariable("productId") Long productId
    ) {
        cartService.removeItem(cartId, productId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<?> clearCart(@PathVariable UUID cartId) {
        cartService.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }
}
