package com.techreturners.weatheripe.repository;

import com.techreturners.weatheripe.model.Weather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class WeatherRepositoryTest {
    @Autowired
    private WeatherRepository weatherRepository;

    @Test
    public void testFindByTemperatureBetweenTemperatureHighLowReturnsWeather() {
        double temperature = 10.2;

        List<Weather> weathers = weatherRepository.findByTemperatureBetweenTemperatureHighLow( temperature);
        assertEquals(weathers.size(), 1);
    }

    @Test
    public void testFindByTemperatureBetweenTemperatureHighLowReturnsWeathers() {
        double temperature = 0;

        List<Weather> weathers = weatherRepository.findByTemperatureBetweenTemperatureHighLow( temperature);
        assertEquals(weathers.size(), 2);
    }

    @Test
    public void testFindByTemperatureBetweenTemperatureHighLowReturnsFailed() {
        double temperature = 100.2;

        List<Weather> weathers = weatherRepository.findByTemperatureBetweenTemperatureHighLow( temperature);
        assertEquals(weathers.size(), 0);
    }
}
