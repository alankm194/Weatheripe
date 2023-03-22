package com.techreturners.weatheripe.recipe.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.weatheripe.exception.NoMatchingCriteriaException;
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
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@DataJpaTest
public class RecipeServiceTest {

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Mock
    private ExternalApiServiceImpl externalApiService;

/*    @Test
    public void getRecipeByWeatherCondition(){
        String query = "https://api.edamam.com/api/recipes/v2?dishType=desserts&dishType=drinks&dishType=egg&dishType=pancake";

        ReflectionTestUtils.setField(recipeService,
                "RECIPE_APP_KEY", "67cafd22003829f89f36cf1800d9f7ca");
        ReflectionTestUtils.setField(recipeService,
                "API_TYPE", "public");
        ReflectionTestUtils.setField(recipeService,
                "RECIPE_APP_ID", "8a3753d7");

        RecipeQueryDTO recipeQueryDTO = new RecipeQueryDTO(query);

        RecipeResponseDTO recipeResponseDTO = (RecipeResponseDTO) recipeService.getRecipeByWeatherCondition(recipeQueryDTO);
        assertThat(recipeResponseDTO.getHits().length).isEqualTo(20);
    }


    @Test
    public void getRecipeByWeatherConditionErrorNoRecipeFound(){
        String query = "https://api.edamam.com/api/recipes/v2?app_key=dummykey&app_id=ba324f9b&type=any&dishType=salad";

        ReflectionTestUtils.setField(recipeService,
                "RECIPE_API_URL", "https://api.edamam.com/api/recipes/v2?&dishType=dess");
        ReflectionTestUtils.setField(recipeService,
                "RECIPE_APP_KEY", "67cafd22003829f89f36cf1800d9f7ca");
        ReflectionTestUtils.setField(recipeService,
                "API_TYPE", "public");
        ReflectionTestUtils.setField(recipeService,
                "RECIPE_APP_ID", "8a3753d7");

        RecipeQueryDTO recipeQueryDTO = new RecipeQueryDTO(query);
        assertThrows(NoMatchingCriteriaException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                RecipeResponseDTO recipeResponseDTO = (RecipeResponseDTO) recipeService.getRecipeByWeatherCondition(recipeQueryDTO);
            }
        });
*/
    }




}
