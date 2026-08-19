package com.codewithmosh.store.core.exceptions;

/**
 * ProductNotFoundException
 */
public class ProductNotFoundException extends ResourceNotFoundException {

    private static String entity = "Product";

    public ProductNotFoundException(String id) {
        super(entity, id);
    }
}
