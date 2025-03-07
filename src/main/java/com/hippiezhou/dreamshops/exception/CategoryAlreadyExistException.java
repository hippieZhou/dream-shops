package com.hippiezhou.dreamshops.exception;

public class CategoryAlreadyExistException extends RuntimeException {
    public CategoryAlreadyExistException(String name) {
        super("Category with name " + name + " already exists");
    }
}
