package com.techreturners.weatheripe.weather.dto;

import lombok.Data;

@Data
public class WeatherValuesDTO {
    private double cloudBase;
    private double cloudCeiling;
    private int cloudCover;
    private double dewPoint;
    private int freezingRainIntensity;
    private int humidity;
    private int precipitationProbability;
    private double pressureSurfaceLevel;
    private int rainIntensity;
    private int sleetIntensity;
    private int snowIntensity;
    private double temperature;
    private double temperatureApparent;
    private int uvHealthConcern;
    private int uvIndex;
    private double visibility;
    private int weatherCode;
    private double windDirection;
    private double windGust;
    private double windSpeed;
}
