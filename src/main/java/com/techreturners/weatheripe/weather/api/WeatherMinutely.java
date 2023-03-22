package com.techreturners.weatheripe.weather.api;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class WeatherMinutely {
    private Timestamp time;
    private WeatherValues values;
}
