package com.techreturners.weatheripe.security.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequestDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}


