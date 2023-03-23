package com.techreturners.weatheripe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.weatheripe.request.LoginRequest;
import com.techreturners.weatheripe.request.SignupRequest;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;


    @Autowired
    public AuthControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }
    @Test
    void testRegisterAndLoginToGetJWTToken() throws Exception {

        SignupRequest request = SignupRequest.builder()
                .email("johnny@email.com")
                .username("johnny")
                .password("fakerpassword")
                .build();

        MvcResult registerResult = this.mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/api/v1/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("User registered successfully!")) )
                .andReturn();

        LoginRequest loginRequest= LoginRequest.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();

        MvcResult loginResult = this.mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/api/v1/auth/signin")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token", notNullValue()))
                .andExpect(jsonPath("$.username", is(loginRequest.getUsername())) )
                .andExpect(jsonPath("$.type", is("Bearer")) )
                .andReturn();
    }

}
