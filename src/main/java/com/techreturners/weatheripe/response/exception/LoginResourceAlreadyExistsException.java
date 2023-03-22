package com.techreturners.weatheripe.response.exception;

public class LoginResourceAlreadyExistsException extends RuntimeException{

    public LoginResourceAlreadyExistsException(String message) {
        super(message);
    }
}
