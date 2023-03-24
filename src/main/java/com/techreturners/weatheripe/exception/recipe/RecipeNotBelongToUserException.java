package com.techreturners.weatheripe.exception.recipe;

public class RecipeNotBelongToUserException extends RuntimeException {
    public RecipeNotBelongToUserException(String message) {
        super(message);
    }
}
