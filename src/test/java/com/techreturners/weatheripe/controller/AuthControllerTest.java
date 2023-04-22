package com.techreturners.weatheripe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.weatheripe.WeathipeApplication;
import com.techreturners.weatheripe.configuration.WebSecurityConfiguration;
import com.techreturners.weatheripe.model.user.UserAccount;
import com.techreturners.weatheripe.repository.UserAccountRepository;
import com.techreturners.weatheripe.security.dto.LoginRequestDTO;
import com.techreturners.weatheripe.security.dto.SignupRequestDTO;

import com.techreturners.weatheripe.security.user.PermissionRole;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest( classes = { WeathipeApplication.class, WebSecurityConfiguration.class })
@AutoConfigureMockMvc

public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @TestConfiguration
    public static class WebClientConfiguration {
        @Bean public PasswordEncoder passwordEncoder() {
            return NoOpPasswordEncoder.getInstance() ;
        }
    }



    @Test
    void testUserRegisterWithValidDetails() throws Exception {
        SignupRequestDTO request = SignupRequestDTO.builder()
                .email("johnny@email.com")
                .username("johnny")
                .password("fakerpassword")
                .build();

        MvcResult registerResult = this.mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/api/v1/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is("User registered successfully!")) )
                .andReturn();

    }

    @Test
    void testUserLoginWithValidDetails() throws Exception {
        String username = "validUsername";
        String password = "validpass";

        UserAccount user = UserAccount.builder()
                .userName(username)
                .email("test@email123fake.com")
                .role(PermissionRole.USER)
                .createdTimestamp(new Date(System.currentTimeMillis()))
                .password(passwordEncoder.encode(password))
                .isActive(true).build();

        userAccountRepository.save(user);

        LoginRequestDTO loginRequestDTO = LoginRequestDTO.builder()
                .username(username)
                .password(password)
                .build();
        MvcResult registerResult = this.mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/api/v1/auth/signin")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(loginRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type", is("Bearer")))
                .andExpect(jsonPath("$.username", is(username)))
                .andExpect(jsonPath("$.token", notNullValue()))
                .andReturn();

    }
}


