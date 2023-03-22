package com.techreturners.weatheripe.service;

import com.techreturners.weatheripe.request.LoginRequest;
import com.techreturners.weatheripe.response.SuccessfulLoginResponse;

public interface AuthService {

    SuccessfulLoginResponse login(LoginRequest loginRequest);


}
