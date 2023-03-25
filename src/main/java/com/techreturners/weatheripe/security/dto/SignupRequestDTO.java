package com.techreturners.weatheripe.security.dto;



import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class SignupRequestDTO {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

}
