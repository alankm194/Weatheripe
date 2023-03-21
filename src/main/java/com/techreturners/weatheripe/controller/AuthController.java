package com.techreturners.weatheripe.controller;


import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.techreturners.weatheripe.model.ERole;
import com.techreturners.weatheripe.model.UserAccount;
import com.techreturners.weatheripe.repository.UserAccountRepository;
import com.techreturners.weatheripe.request.LoginRequest;
import com.techreturners.weatheripe.request.SignupRequest;
import com.techreturners.weatheripe.response.ErrorResponse;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techreturners.weatheripe.response.SuccessfulLoginResponse;
import com.techreturners.weatheripe.response.MessageResponse;
import com.techreturners.weatheripe.security.JwtUtils;
import com.techreturners.weatheripe.security.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<SuccessfulLoginResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new SuccessfulLoginResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userAccountRepository.existsByUserName(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse("Error: Username is already taken!","400"));
        }

        if (userAccountRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse("Error: Email is already in use!", "400"));
        }


        UserAccount user = UserAccount.builder()
                .userName(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .role(ERole.ROLE_USER)
                .createdTimestamp(new Date(System.currentTimeMillis()))
                .password(encoder.encode(signUpRequest.getPassword()))
                .isActive(true).build();


        user.setRole(ERole.ROLE_USER);
        userAccountRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
