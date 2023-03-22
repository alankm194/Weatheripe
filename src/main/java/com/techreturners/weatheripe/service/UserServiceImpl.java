package com.techreturners.weatheripe.service;

import com.techreturners.weatheripe.model.ERole;
import com.techreturners.weatheripe.model.UserAccount;
import com.techreturners.weatheripe.repository.UserAccountRepository;
import com.techreturners.weatheripe.request.SignupRequest;
import com.techreturners.weatheripe.response.exception.EmailAlreadyExistsException;
import com.techreturners.weatheripe.response.exception.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    PasswordEncoder encoder;


    public UserAccount create(SignupRequest userSignUp) {
        if (userAccountRepository.existsByUserName(userSignUp.getUsername())) {
            throw new UsernameAlreadyExistsException(userSignUp.getUsername());
        }

        if (userAccountRepository.existsByEmail(userSignUp.getEmail())) {
            throw new EmailAlreadyExistsException(userSignUp.getEmail());
        }

        UserAccount user = UserAccount.builder()
                .userName(userSignUp.getUsername())
                .email(userSignUp.getEmail())
                .role(ERole.ROLE_USER)
                .createdTimestamp(new Date(System.currentTimeMillis()))
                .password(encoder.encode(userSignUp.getPassword()))
                .isActive(true).build();

        userAccountRepository.save(user);
        return user;
    }
}
