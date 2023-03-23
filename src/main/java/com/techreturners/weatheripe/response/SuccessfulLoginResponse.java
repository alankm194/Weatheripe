package com.techreturners.weatheripe.response;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class SuccessfulLoginResponse {
    private String token;
    @Builder.Default
    private final String type = "Bearer";
    private String username;


}
