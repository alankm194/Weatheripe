package com.techreturners.weatheripe.repository;

import com.techreturners.weatheripe.model.FoodForWeather;
import com.techreturners.weatheripe.model.weather.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodForWeatherRepository extends JpaRepository<FoodForWeather, Long> {
    List<FoodForWeather> findByWeatherIdIn(List<Weather> weatherId);
}
