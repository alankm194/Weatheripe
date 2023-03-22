package com.techreturners.weatheripe.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorType {

    private String message;
    private String code;
    private String error;
    private String classType;
}
