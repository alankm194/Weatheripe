package com.techreturners.weatheripe.recipe.service;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.weatheripe.configuration.SecretConfiguration;
import com.techreturners.weatheripe.exception.ExceptionMessages;
import com.techreturners.weatheripe.exception.NoMatchingCriteriaException;
import com.techreturners.weatheripe.exception.RecipeNotFoundException;
import com.techreturners.weatheripe.exception.ResourceNotFoundException;
import com.techreturners.weatheripe.external.dto.ExternalRequestDto;
import com.techreturners.weatheripe.external.service.ExternalApiServiceImpl;
import com.techreturners.weatheripe.recipe.dto.RecipeResponseDTO;
import com.techreturners.weatheripe.weather.dto.RecipeQueryDTO;
import com.techreturners.weatheripe.weather.dto.WeatherApiDTO;
import org.checkerframework.checker.nullness.qual.AssertNonNullIfNonNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@DataJpaTest
public class RecipeServiceTest {

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Mock
    private SecretConfiguration secretConfiguration;

    @Mock
    private WebClient webClient;

//    @Mock
@InjectMocks
private ExternalApiServiceImpl externalApiService;

//    @Test
//    public void getRecipeByWeatherCondition(){
//        String query = "https://api.edamam.com/api/recipes/v2?dishType=desserts&dishType=drinks&dishType=egg&dishType=pancake";
//
//        ReflectionTestUtils.setField(recipeService,
//                "RECIPE_APP_KEY", "67cafd22003829f89f36cf1800d9f7ca");
//        ReflectionTestUtils.setField(recipeService,
//                "API_TYPE", "public");
//        ReflectionTestUtils.setField(recipeService,
//                "RECIPE_APP_ID", "8a3753d7");
//
//        RecipeQueryDTO recipeQueryDTO = new RecipeQueryDTO(query);
//
//        RecipeResponseDTO recipeResponseDTO = (RecipeResponseDTO) recipeService.getRecipeByWeatherCondition(recipeQueryDTO);
//        assertThat(recipeResponseDTO.getHits().length).isEqualTo(20);
//    }


//    @Test
//    public void getRecipeByWeatherCondition() throws IOException {
//
//        ReflectionTestUtils.setField(recipeService,
//                "API_TYPE", "public");
//        when(secretConfiguration.recipeAppKey()).thenReturn("67cafd22003829f89f36cf1800d9f7ca");
//        when(secretConfiguration.recipeAppId()).thenReturn("8a3753d7");
//        String query = "https://api.edamam.com/api/recipes/v2?dishType=desserts&dishType=drinks&dishType=egg&dishType=pancake";
//
//        // To read the json file to form the weatherApiObj
////        Resource resource = new ClassPathResource("/edamam_sample_recipe_success.json");
////
////        ObjectMapper objectMapper = new ObjectMapper();
////        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
////        RecipeResponseDTO recipeResponseDTO = objectMapper.readValue(resource.getInputStream(), RecipeResponseDTO.class);
//
//        RecipeQueryDTO recipeQueryDTO = new RecipeQueryDTO(query);
//        RecipeResponseDTO recipeResponseDTO = (RecipeResponseDTO) recipeService.getRecipeByWeatherCondition(recipeQueryDTO);
//        System.out.println(recipeResponseDTO.getHits().length);
//        assertEquals(recipeResponseDTO.getHits().length,20);
//
//    }




//    @Test
//    public void getRecipeByWeatherConditionErrorNoRecipeFound() {
//        String query = "https://api.edamam.com/api/recipes/v2?app_key=dummykey&app_id=ba324f9b&type=any&dishType=salad";
//
//        ReflectionTestUtils.setField(recipeService,
//                "RECIPE_API_URL", query);
//
//        when(secretConfiguration.recipeAppId()).thenReturn("8a3753d7");
//        when(secretConfiguration.recipeAppKey()).thenReturn("67cafd22003829f89f36cf1800d9f7ca");
//
//        RecipeQueryDTO recipeQueryDTO = new RecipeQueryDTO(query);
//        assertThrows(NoMatchingCriteriaException.class, new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                RecipeResponseDTO recipeResponseDTO = (RecipeResponseDTO) recipeService.getRecipeByWeatherCondition(recipeQueryDTO);
//            }
//        });
//
//    }




}
