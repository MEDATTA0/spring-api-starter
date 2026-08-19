package com.codewithmosh.store.core.exceptions;

/**
 * CartNotFoundException
 */
public class CartNotFoundException extends ResourceNotFoundException {

    private static String entity = "Cart";

    public CartNotFoundException(String id) {
        super(entity, id);
    }
}
