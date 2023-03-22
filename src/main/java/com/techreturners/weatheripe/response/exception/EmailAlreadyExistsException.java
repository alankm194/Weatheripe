package com.techreturners.weatheripe.response.exception;

public class EmailAlreadyExistsException extends LoginResourceAlreadyExistsException {

    private static final String ERROR_MESSAGE_FORMAT = "email %s already exists";

    public EmailAlreadyExistsException(String email) {
        super(String.format(ERROR_MESSAGE_FORMAT, email));
    }
}
