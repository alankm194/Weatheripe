package com.techreturners.weatheripe.response;

public class InvalidJwtResponse {

    private String message;
    private static final String STATUS = "401";


    public InvalidJwtResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return STATUS;
    }



}
