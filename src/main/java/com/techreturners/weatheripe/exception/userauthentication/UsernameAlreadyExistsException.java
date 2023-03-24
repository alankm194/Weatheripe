package com.techreturners.weatheripe.exception.userauthentication;

public class UsernameAlreadyExistsException extends LoginResourceAlreadyExistsException {

    private static final String ERROR_MESSAGE_FORMAT = "Username %s already exists.";


    public UsernameAlreadyExistsException(String username) {
        super(String.format(ERROR_MESSAGE_FORMAT, username));
    }

}
