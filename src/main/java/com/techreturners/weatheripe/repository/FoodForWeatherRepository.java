package com.techreturners.weatheripe.repository;

import com.techreturners.weatheripe.model.FoodForWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodForWeatherRepository extends JpaRepository<FoodForWeather, Long> {

}
