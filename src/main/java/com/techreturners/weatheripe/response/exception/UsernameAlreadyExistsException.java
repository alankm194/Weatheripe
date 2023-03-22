package com.techreturners.weatheripe.response.exception;

public class UsernameAlreadyExistsException extends LoginResourceAlreadyExistsException {

    public static final String ERROR_MESSAGE_FORMAT = "Username %s already exists.";

    public UsernameAlreadyExistsException(String username) {
        super( String.format(ERROR_MESSAGE_FORMAT, username));
    }



}
