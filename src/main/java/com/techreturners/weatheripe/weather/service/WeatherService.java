package com.techreturners.weatheripe.weather.service;

import com.techreturners.weatheripe.external.dto.ResponseDTO;

public interface WeatherService {
    ResponseDTO getWeatherByLocation(String location) ;
}
