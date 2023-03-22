package com.techreturners.weatheripe.recipe.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.weatheripe.external.dto.ResponseDTO;
import com.techreturners.weatheripe.external.service.ExternalApiService;
import com.techreturners.weatheripe.external.service.ExternalApiServiceImpl;
import com.techreturners.weatheripe.model.DishType;
import com.techreturners.weatheripe.model.FoodForWeather;
import com.techreturners.weatheripe.model.Weather;
import com.techreturners.weatheripe.recipe.dto.RecipeResponseDTO;
import com.techreturners.weatheripe.repository.FoodForWeatherRepository;
import com.techreturners.weatheripe.repository.WeatherRepository;
import com.techreturners.weatheripe.weather.dto.RecipeQueryDTO;
import com.techreturners.weatheripe.weather.dto.WeatherApiDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
public class RecipeServiceTest {

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Mock
    private ExternalApiServiceImpl externalApiService;

    @Mock
    private FoodForWeatherRepository mockFoodForWeatherRepository;
    @Mock
    private WeatherRepository WeatherRepository;


//    @Test
//    public void getRecipeByWeatherCondition() throws IOException {
//        String query = "https://api.edamam.com/api/recipes/v2?app_key=dummykey&app_id=ba324f9b&type=any&dishType=salad";
//
//        // To read the json file to form the weatherApiObj
//        Resource resource = new ClassPathResource("/good_recipe_response.json");
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        RecipeResponseDTO mockRecipeResponseDTO = objectMapper.readValue(resource.getInputStream(), RecipeResponseDTO.class);
//
//
//        RecipeQueryDTO recipeQueryDTO = new RecipeQueryDTO(query);
//
//        RecipeResponseDTO recipeResponseDTO = (RecipeResponseDTO) recipeService.getRecipeByWeatherCondition(recipeQueryDTO);
//        assertThat(recipeResponseDTO).isNull();
//    }




}
