package com.techreturners.weatheripe.exception.userauthentication;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}