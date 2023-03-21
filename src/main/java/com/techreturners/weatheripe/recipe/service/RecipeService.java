package com.techreturners.weatheripe.recipe.service;

import com.techreturners.weatheripe.exception.NoMatchingCriteriaException;
import com.techreturners.weatheripe.external.dto.ResponseDTO;
import com.techreturners.weatheripe.model.FoodForWeather;

public interface RecipeService {

    ResponseDTO getRecipeByWeatherCondition(ResponseDTO weatherResponseDTO) throws NoMatchingCriteriaException;
}
