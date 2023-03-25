package com.techreturners.weatheripe.security.dto;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class SuccessfulLoginDTO {
    private String token;
    @Builder.Default
    private final String type = "Bearer";
    private String username;


}
