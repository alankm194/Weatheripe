package com.techreturners.weatheripe.weather.api;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WeatherMinutely {
    private LocalDateTime time;
    private WeatherValues values;
}
