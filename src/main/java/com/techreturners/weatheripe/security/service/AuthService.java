package com.techreturners.weatheripe.security.service;

import com.techreturners.weatheripe.security.dto.LoginRequestDTO;
import com.techreturners.weatheripe.security.dto.SuccessfulLoginDTO;

public interface AuthService {

    SuccessfulLoginDTO login(LoginRequestDTO loginRequestDTO);

    String extractUsernameFromToken(String token);
}
