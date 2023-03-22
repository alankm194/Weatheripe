package com.techreturners.weatheripe.weather.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class WeatherMinutelyDTO {
    private Timestamp time;
    private WeatherValuesDTO values;
}
