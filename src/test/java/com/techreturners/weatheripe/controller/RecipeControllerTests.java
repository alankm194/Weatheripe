package com.techreturners.weatheripe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.weatheripe.exception.ExceptionHandlerController;
import com.techreturners.weatheripe.external.dto.ResponseDTO;
import com.techreturners.weatheripe.recipe.dto.RecipeResponseDTO;
import com.techreturners.weatheripe.recipe.service.RecipeServiceImpl;
import com.techreturners.weatheripe.repository.UserAccountRepository;
import com.techreturners.weatheripe.user.UserAccountServiceImpl;
import com.techreturners.weatheripe.user.dto.UserRecipeBookResponseDTO;
import com.techreturners.weatheripe.weather.dto.RecipeQueryDTO;
import com.techreturners.weatheripe.weather.dto.WeatherApiDTO;
import com.techreturners.weatheripe.weather.service.WeatherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
public class RecipeControllerTests {
    @Mock
    private WeatherServiceImpl mockWeatherServiceImpl;

    @Mock
    private UserAccountServiceImpl mockUserAccountServiceImpl;

    @Mock
    private RecipeServiceImpl mockRecipeServiceImpl;

    @InjectMocks
    private RecipeController recipeController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;


    @BeforeEach
    public void setup() {
        mockMvcController = MockMvcBuilders.standaloneSetup(recipeController)
                .setControllerAdvice(new ExceptionHandlerController()).build();
        mapper = new ObjectMapper();

    }

    @Test
    public void testGetRecipeByLocationReturnsRecipes() throws Exception {
        String location = "London";

        WeatherApiDTO weatherApiDTO = new WeatherApiDTO();
        ResponseDTO recipeQueryDTO = new RecipeQueryDTO("");
        ResponseDTO recipeResponseDTO = new RecipeResponseDTO();

        when(mockWeatherServiceImpl.getWeatherByLocation(location)).thenReturn(weatherApiDTO);
        when(mockWeatherServiceImpl.buildExternalRecipeAPIQuery(weatherApiDTO)).thenReturn(recipeQueryDTO);
        when(mockRecipeServiceImpl.getRecipeByWeatherCondition(recipeQueryDTO)).thenReturn(recipeResponseDTO);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/recipe/"+location))
                .andExpect(MockMvcResultMatchers.status().isOk());
        // TODO: add more expect values when the recipeAPI is completed
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Book One"));
    }

//TODO Fix Test

//    @Test
//    public void testGetWeatherByLocationForUser() throws Exception {
//        String location = "London";
//        UserRecipeBookResponseDTO  userRecipeBookResponseDTO = new UserRecipeBookResponseDTO();
//
//        when(mockWeatherServiceImpl.getRecipeByLocationForUser(location,"")).thenReturn(userRecipeBookResponseDTO);
//        this.mockMvcController.perform(
//                        MockMvcRequestBuilders.get("/api/v1/recipe/user/"+location))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//    }



}
