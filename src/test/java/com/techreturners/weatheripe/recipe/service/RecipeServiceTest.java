package com.techreturners.weatheripe.recipe.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.weatheripe.exception.ExceptionMessages;
import com.techreturners.weatheripe.exception.InvalidApiKeyException;
import com.techreturners.weatheripe.external.dto.ExternalRequestDto;
import com.techreturners.weatheripe.external.service.ExternalApiServiceImpl;
import com.techreturners.weatheripe.recipe.dto.RecipeResponseDTO;
import com.techreturners.weatheripe.weather.dto.RecipeQueryDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@DataJpaTest
public class RecipeServiceTest {

    @Mock
    private WebClient webClient;

    @Mock
    private ExternalApiServiceImpl externalApiService;


    @Test
    public void testGetRecipeByWeatherCondition() throws IOException {

        String query = "https://api.edamam.com/api/recipes/v2?dishType=salad&cuisineType=italian&app_key=67cafd22003829f89f36cf1800d9f7ca&app_id=8a3753d7&type=public";
        RecipeQueryDTO recipeQueryDTO = RecipeQueryDTO.builder().query(query).build();
        ExternalRequestDto externalRequestDto = new ExternalRequestDto(query, recipeQueryDTO);

        Resource resource = new ClassPathResource("/good_recipe_response.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        RecipeResponseDTO recipeResponseDTO = objectMapper.readValue(resource.getInputStream(), RecipeResponseDTO.class);
        when(externalApiService.getResourcesByUri(externalRequestDto)).thenReturn(recipeResponseDTO);

        assertEquals(recipeResponseDTO.getHits().length, 20);

    }

    @Test
    public void testGetRecipeByWeatherConditionInvalidApiKey() throws IOException {

        String query = "https://api.edamam.com/api/recipes/v2?dishType=salad&cuisineType=italian&app_key=12121212&app_id=8a3753d7&type=public";
        RecipeQueryDTO recipeQueryDTO = RecipeQueryDTO.builder().query(query).build();
        ExternalRequestDto externalRequestDto = new ExternalRequestDto(query, recipeQueryDTO);

        doThrow(new InvalidApiKeyException(ExceptionMessages.INVALID_API_KEY))
                .when(externalApiService).getResourcesByUri(externalRequestDto);
    }


    @Test
    public void testGetRecipeByWeatherConditionNoRecords() throws IOException {

        String query = "https://api.edamam.com/api/recipes/v2?app_key=67cafd22003829f89f36cf1800d9f7ca&app_id=8a3753d7&type=public";
        RecipeQueryDTO recipeQueryDTO = RecipeQueryDTO.builder().query(query).build();
        ExternalRequestDto externalRequestDto = new ExternalRequestDto(query, recipeQueryDTO);

        Resource resource = new ClassPathResource("/good_recipe_response_noRecords.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        RecipeResponseDTO recipeResponseDTO = objectMapper.readValue(resource.getInputStream(), RecipeResponseDTO.class);
        when(externalApiService.getResourcesByUri(externalRequestDto)).thenReturn(recipeResponseDTO);

        assertEquals(recipeResponseDTO.getHits().length, 0);

    }


}
