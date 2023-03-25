package com.techreturners.weatheripe.recipe.service;

import com.techreturners.weatheripe.exception.*;
import com.techreturners.weatheripe.configuration.SecretConfiguration;
import com.techreturners.weatheripe.exception.ExceptionMessages;
import com.techreturners.weatheripe.exception.recipe.NoMatchingCriteriaException;
import com.techreturners.weatheripe.exception.recipe.RecipeNotFoundException;
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
    @Autowired
    private SecretConfiguration secretConfiguration;

    @Value("${recipe.api.type}")
    private String API_TYPE;


    @Override
    public ResponseDTO getRecipeByWeatherCondition(ResponseDTO weatherResponseDTO) {
        RecipeQueryDTO recipeQueryDTO = (RecipeQueryDTO) weatherResponseDTO;

        StringBuilder query = new StringBuilder(recipeQueryDTO.getQuery());
        query.append("&app_key=").append(secretConfiguration.recipeAppKey());
        query.append("&app_id=").append(secretConfiguration.recipeAppId());
        query.append("&type=").append(API_TYPE);
        log.info(query.toString());
        ExternalRequestDto externalRequestDto = new ExternalRequestDto(query.toString(), new RecipeResponseDTO());
        RecipeResponseDTO recipeResponseDTO = null;
        try {
            recipeResponseDTO = (RecipeResponseDTO) externalApiService.getResourcesByUri(externalRequestDto);

        } catch (ResourceNotFoundException e) {
            throw new RecipeNotFoundException(ExceptionMessages.NO_MATCHING_FOOD_CRITERIA);
        } catch (InvalidApiKeyException e) {
            throw new RecipeNotFoundException(ExceptionMessages.INVALID_API_KEY);
        }
        if (recipeResponseDTO == null || recipeResponseDTO.getHits() == null || recipeResponseDTO.getHits().length == 0) {
            throw new NoMatchingCriteriaException(ExceptionMessages.NO_MATCHING_RECIPES_FOOD_CRITERIA);
        }

        return recipeResponseDTO;
    }
}
