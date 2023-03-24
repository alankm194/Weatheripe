package com.techreturners.weatheripe.exception;

public class RecipeNotBelongToUserException extends RuntimeException {
    public RecipeNotBelongToUserException(String message) {
        super(message);
    }
}
