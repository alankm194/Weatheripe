package com.techreturners.weatheripe.response;

import lombok.Getter;

@Getter
public record ErrorResponse(String errorMessage, String Status) {

}
