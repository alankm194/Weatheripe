package com.techreturners.weatheripe.recipe.service;

import com.techreturners.weatheripe.exception.recipe.NoMatchingCriteriaException;
import com.techreturners.weatheripe.external.dto.ResponseDTO;

public interface RecipeService {

    ResponseDTO getRecipeByWeatherCondition(ResponseDTO weatherResponseDTO) throws NoMatchingCriteriaException;
}
