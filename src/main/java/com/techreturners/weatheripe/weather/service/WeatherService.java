package com.techreturners.weatheripe.weather.service;

import com.techreturners.weatheripe.exception.UserSessionNotFoundException;
import com.techreturners.weatheripe.external.dto.ResponseDTO;
import com.techreturners.weatheripe.weather.dto.WeatherApiDTO;

public interface WeatherService {
    ResponseDTO getWeatherByLocation(String location);
    ResponseDTO buildExternalRecipeAPIQuery(WeatherApiDTO weatherApiObj);

    ResponseDTO getRecipeByLocationForUser(String location, String userToken) throws UserSessionNotFoundException;
}
