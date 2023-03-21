package com.techreturners.weatheripe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.weatheripe.request.SignupRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class testAuthController {

    @Autowired
    private MockMvc mockMvcController;

    @InjectMocks
    private AuthController AuthController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    //private final UserTestDataFactory userTestDataFactory;


    @BeforeEach
    public void setup(){
        mockMvcController = MockMvcBuilders.standaloneSetup(AuthController)
                .build();
    }

    @DisplayName("Test Signing in with a valid user, email and password")
    @Test
    @WithAnonymousUser
    void testSignUp() throws Exception {

        SignupRequest request = new SignupRequest();

        MvcResult createResult = this.mockMvcController
                .perform(
                        MockMvcRequestBuilders.post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(header().exists(HttpHeaders.AUTHORIZATION))
                .andReturn();
    }

}
