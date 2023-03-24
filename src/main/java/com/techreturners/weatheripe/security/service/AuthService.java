package com.techreturners.weatheripe.security.service;

import com.techreturners.weatheripe.request.LoginRequest;
import com.techreturners.weatheripe.response.SuccessfulLoginResponse;

public interface AuthService {

    SuccessfulLoginResponse login(LoginRequest loginRequest);

    String extractUsernameFromToken(String token);
}
