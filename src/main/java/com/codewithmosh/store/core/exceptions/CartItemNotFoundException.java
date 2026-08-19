package com.codewithmosh.store.core.exceptions;

/**
 * CartItemNotFoundException
 */
public class CartItemNotFoundException extends ResourceNotFoundException {

    public static String entity = "CartItem";

    public CartItemNotFoundException(String id) {
        super(entity, id);
    }
}
