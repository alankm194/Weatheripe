package com.techreturners.weatheripe.weather.dto;

import lombok.Data;

import java.util.List;

@Data
public class WeatherTimelinesDTO {
    private List<WeatherMinutelyDTO> minutely;
}
