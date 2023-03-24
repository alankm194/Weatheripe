package com.techreturners.weatheripe.security.service;

import com.techreturners.weatheripe.model.PermissionRole;
import com.techreturners.weatheripe.model.UserAccount;
import com.techreturners.weatheripe.repository.UserAccountRepository;
import com.techreturners.weatheripe.request.SignupRequest;
import com.techreturners.weatheripe.response.exception.EmailAlreadyExistsException;
import com.techreturners.weatheripe.response.exception.UsernameAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserAccountRepository userAccountRepository;

    private final PasswordEncoder encoder;

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
                .role(PermissionRole.USER)
                .createdTimestamp(new Date(System.currentTimeMillis()))
                .password(encoder.encode(userSignUp.getPassword()))
                .isActive(true).build();

        userAccountRepository.save(user);
        return user;
    }
}
