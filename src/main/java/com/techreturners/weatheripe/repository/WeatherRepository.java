package com.techreturners.weatheripe.repository;

import com.techreturners.weatheripe.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {
//    @Query("SELECT w FROM Weather w WHERE w.temperatureHigh >= :temperature and w.temperatureLow <= :temperature")
//    List<Weather> findByTemperatureBetweenTemperatureMinMax(double temperature);
}
