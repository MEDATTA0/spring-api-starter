package com.codewithmosh.store.core.exceptions;

/**
 * ResourceNotFoundException
 */

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String entity, String id) {
        super("%s with ID %s Not Found ".formatted(entity, id));
    }
}
