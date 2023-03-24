package com.techreturners.weatheripe.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.weatheripe.request.LoginRequest;
import com.techreturners.weatheripe.request.SignupRequest;
import com.techreturners.weatheripe.user.dto.RecipeBookDTO;
import com.techreturners.weatheripe.user.dto.RecipeBookRequestDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserServiceControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }


//    @BeforeAll
//    public void init() throws Exception {
//        SignupRequest request = SignupRequest.builder()
//                .email("johnny@email.com")
//                .username("johnny")
//                .password("fakerpassword")
//                .build();
//
//        MvcResult registerResult = this.mockMvc
//                .perform(
//                        MockMvcRequestBuilders.post("/api/v1/auth/signup")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(request)))
//                .andReturn();
//
//        LoginRequest loginRequest= LoginRequest.builder()
//                .username(request.getUsername())
//                .password(request.getPassword())
//                .build();
//
//        MvcResult loginResult = this.mockMvc
//                .perform(
//                        MockMvcRequestBuilders.post("/api/v1/auth/signin")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(objectMapper.writeValueAsString(loginRequest)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.token", notNullValue()))
//                .andExpect(jsonPath("$.username", is(loginRequest.getUsername())) )
//                .andExpect(jsonPath("$.type", is("Bearer")) )
//                .andReturn();
//    }


//    @Test
//    @WithMockUser(username = "YourUserName", roles={"ROLE_USER"})
//    public void testUpdateRecipeBook() throws Exception {
//
////        RecipeBookDTO bookDTO1 =  RecipeBookDTO.builder().recipeId(1L).rating(5d).isFavourite(true).build();
////        RecipeBookDTO bookDTO2 =  RecipeBookDTO.builder().recipeId(2L).rating(4.5d).isFavourite(false).build();
////        RecipeBookRequestDTO recipeBookRequestDTO = RecipeBookRequestDTO.builder()
////                .recipeBooks(new RecipeBookDTO[]{bookDTO1,bookDTO2}).build();
////
////
////        MvcResult recipeUpdate = this.mockMvc
////                .perform(
////                        MockMvcRequestBuilders.patch("/api/v1/user/updateRecipeBook")
////                                .contentType(MediaType.APPLICATION_JSON)
////                                .header("Authorization", "Bearer " + "accessToken")
////                                .content(objectMapper.writeValueAsString(recipeBookRequestDTO)))
////                .andExpect(status().isOk())
//////                .andExpect(jsonPath("$.token", notNullValue()))
//////                .andExpect(jsonPath("$.username", is(loginRequest.getUsername())) )
//////                .andExpect(jsonPath("$.type", is("Bearer")) )
////                .andReturn();
//
//
//    }

}
