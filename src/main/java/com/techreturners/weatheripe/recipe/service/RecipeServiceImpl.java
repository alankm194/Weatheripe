package com.techreturners.weatheripe.recipe.service;

import com.techreturners.weatheripe.external.dto.ExternalRequestDto;
import com.techreturners.weatheripe.external.dto.ResponseDTO;
import com.techreturners.weatheripe.external.service.ExternalApiService;
import com.techreturners.weatheripe.external.service.ExternalApiServiceImpl;
import com.techreturners.weatheripe.model.FoodForWeather;
import com.techreturners.weatheripe.recipe.dto.RecipeResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.text.MessageFormat;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private ExternalApiService externalApiService;

    @Value("${recipe.api.url}")
    private String RECIPE_API_URL;

    @Value("${recipe.app.key}")
    private String RECIPE_APP_KEY;

    @Value("${recipe.api.type}")
    private String API_TYPE ;

    @Value("${recipe.app.id}")
    private String RECIPE_APP_id;

    @Override
    public ResponseDTO getRecipeByWeatherCondition(FoodForWeather foodForWeather) {

        String uri = String.format("%s?app_id=%s&app_key=%s&type=%s&dishType=%s",
                RECIPE_API_URL, RECIPE_APP_id, RECIPE_APP_KEY, API_TYPE, foodForWeather.getDishType().getDishTypeLabel());
        log.info("Recipe URL : "+uri);
        ExternalRequestDto externalRequestDto = new ExternalRequestDto(uri,new RecipeResponseDTO());
        RecipeResponseDTO recipeResponseDTO = (RecipeResponseDTO)externalApiService.getResourcesByUri(externalRequestDto);

        return recipeResponseDTO ;
    }
}
