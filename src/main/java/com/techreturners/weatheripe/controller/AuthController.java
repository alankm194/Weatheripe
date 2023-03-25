package com.techreturners.weatheripe.controller;

import com.techreturners.weatheripe.exception.ErrorResponseDTO;
import com.techreturners.weatheripe.security.dto.LoginRequestDTO;
import com.techreturners.weatheripe.security.dto.SignupRequestDTO;
import com.techreturners.weatheripe.security.service.AuthService;
import com.techreturners.weatheripe.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Sign in as a User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Signed in successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SuccessfulLoginDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid details supplied supplied",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping("/signin")
    public ResponseEntity<SuccessfulLoginDTO> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

    @Operation(summary = "Sign up for the Service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SuccessfulLoginDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Incorrect or invalid user details supplied",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping("/signup")
    public ResponseEntity<UserMessageDTO> registerUser(@Valid @RequestBody SignupRequestDTO signUpRequestDTO) {
        userService.create(signUpRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserMessageDTO("User registered successfully!"));
    }
}
