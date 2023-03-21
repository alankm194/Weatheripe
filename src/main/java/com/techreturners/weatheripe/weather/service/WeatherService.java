package com.techreturners.weatheripe.weather.service;

import com.techreturners.weatheripe.external.dto.ResponseDTO;
import com.techreturners.weatheripe.weather.api.WeatherApiObj;

public interface WeatherService {
    ResponseDTO getWeatherByLocation(String location);
    String buildExternalRecipeAPIQuery(WeatherApiObj weatherApiObj);

    ResponseDTO getRecipeByLocation(String location);
}
