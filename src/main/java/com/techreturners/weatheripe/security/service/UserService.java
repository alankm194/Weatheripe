package com.techreturners.weatheripe.security.service;

import com.techreturners.weatheripe.model.UserAccount;
import com.techreturners.weatheripe.request.SignupRequest;

public interface UserService {

    public UserAccount create(SignupRequest userSignUp);

}
