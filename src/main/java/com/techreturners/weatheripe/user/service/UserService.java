package com.techreturners.weatheripe.user.service;

import com.techreturners.weatheripe.model.user.UserAccount;
import com.techreturners.weatheripe.security.dto.SignupRequestDTO;

public interface UserService {

    public UserAccount create(SignupRequestDTO userSignUp);

}
