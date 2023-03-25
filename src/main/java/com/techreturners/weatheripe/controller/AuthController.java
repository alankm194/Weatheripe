package com.techreturners.weatheripe.controller;

import com.techreturners.weatheripe.security.dto.LoginRequestDTO;
import com.techreturners.weatheripe.security.dto.SignupRequestDTO;
import com.techreturners.weatheripe.security.service.AuthService;
import com.techreturners.weatheripe.user.service.UserService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techreturners.weatheripe.security.dto.SuccessfulLoginDTO;
import com.techreturners.weatheripe.user.dto.UserMessageDTO;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;


    @PostMapping("/signin")
    public ResponseEntity<SuccessfulLoginDTO> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserMessageDTO> registerUser(@Valid @RequestBody SignupRequestDTO signUpRequestDTO) {
        userService.create(signUpRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserMessageDTO("User registered successfully!"));
    }
}
