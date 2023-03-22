package com.techreturners.weatheripe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.weatheripe.exception.ExceptionHandlerController;
import com.techreturners.weatheripe.external.dto.DummyReceipeQueryDTO;
import com.techreturners.weatheripe.external.dto.ResponseDTO;
import com.techreturners.weatheripe.weather.RecipeController;
import com.techreturners.weatheripe.weather.service.WeatherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

        ResponseDTO recipeApiObj = new DummyReceipeQueryDTO("");

        when(mockWeatherServiceImpl.getRecipeByLocation(location)).thenReturn(recipeApiObj);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/recipe/"+location))
                .andExpect(MockMvcResultMatchers.status().isOk());
        // TODO: add more expect values when the recipeAPI is completed
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Book One"));
    }
}
