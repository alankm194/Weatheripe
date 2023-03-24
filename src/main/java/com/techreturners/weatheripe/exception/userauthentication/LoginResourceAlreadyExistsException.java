package com.techreturners.weatheripe.exception.userauthentication;

public abstract class LoginResourceAlreadyExistsException extends RuntimeException{

    protected LoginResourceAlreadyExistsException(String message) {
        super(message);
    }
}
