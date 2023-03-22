package com.techreturners.weatheripe.service;

import com.techreturners.weatheripe.model.UserAccount;
import com.techreturners.weatheripe.request.SignupRequest;

public interface UserService {

    public UserAccount create(SignupRequest userSignUp);

}
