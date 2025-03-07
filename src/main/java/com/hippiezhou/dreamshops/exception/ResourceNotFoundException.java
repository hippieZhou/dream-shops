package com.hippiezhou.dreamshops.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String name) {
        super("Resource with name " + name + " not found");
    }

    public ResourceNotFoundException(Long id) {
        super("Resource with id " + id + " not found");
    }
}
