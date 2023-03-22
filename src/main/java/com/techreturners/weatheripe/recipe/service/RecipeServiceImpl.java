package com.techreturners.weatheripe.recipe.service;

import com.techreturners.weatheripe.exception.ExceptionMessages;
import com.techreturners.weatheripe.exception.NoMatchingCriteriaException;
import com.techreturners.weatheripe.weather.dto.RecipeQueryDTO;
import com.techreturners.weatheripe.external.dto.ExternalRequestDto;
import com.techreturners.weatheripe.external.dto.ResponseDTO;
import com.techreturners.weatheripe.external.service.ExternalApiService;
import com.techreturners.weatheripe.recipe.dto.RecipeResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    private String API_TYPE;

    @Value("${recipe.app.id}")
    private String RECIPE_APP_ID;

    @Override
    public ResponseDTO getRecipeByWeatherCondition(ResponseDTO weatherResponseDTO) throws NoMatchingCriteriaException {


        RecipeQueryDTO recipeQueryDTO = (RecipeQueryDTO)weatherResponseDTO;
        log.info(recipeQueryDTO.getQuery());
        StringBuilder query = new StringBuilder(recipeQueryDTO.getQuery());
        query.append("&app_key=").append(RECIPE_APP_KEY);
        query.append("&app_id=").append(RECIPE_APP_ID);
        query.append("&type=").append(API_TYPE);

        ExternalRequestDto externalRequestDto = new ExternalRequestDto(query.toString(), new RecipeResponseDTO());
        RecipeResponseDTO recipeResponseDTO = (RecipeResponseDTO) externalApiService.getResourcesByUri(externalRequestDto);

        if(recipeResponseDTO == null || recipeResponseDTO.getHits() == null || recipeResponseDTO.getHits().length == 0 ){
            throw new NoMatchingCriteriaException(ExceptionMessages.NO_MATCHING_RECIPES_FOOD_CRITERIA);
        }

        return recipeResponseDTO;
    }
}
