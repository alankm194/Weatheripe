package com.techreturners.weatheripe.weather.api;

import lombok.Data;

import java.util.List;

@Data
public class WeatherTimelines {
    private List<WeatherMinutely> minutely;
}
