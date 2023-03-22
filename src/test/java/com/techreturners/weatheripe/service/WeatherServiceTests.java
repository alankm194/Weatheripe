package com.techreturners.weatheripe.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.weatheripe.model.DishType;
import com.techreturners.weatheripe.model.FoodForWeather;
import com.techreturners.weatheripe.model.Weather;
import com.techreturners.weatheripe.repository.FoodForWeatherRepository;
import com.techreturners.weatheripe.repository.WeatherRepository;
import com.techreturners.weatheripe.weather.api.WeatherApiObj;
import com.techreturners.weatheripe.weather.service.WeatherServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.util.ReflectionTestUtils;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DataJpaTest
public class WeatherServiceTests {

    @Mock
    private WeatherRepository mockWeatherRepository;

    @Mock
    private FoodForWeatherRepository mockFoodForWeatherRepository;

    @InjectMocks
    private WeatherServiceImpl weatherServiceImpl;



    @Test
    public void testBuildExternalRecipeAPIQueryReturnQueryString() throws Exception {
        ReflectionTestUtils.setField(weatherServiceImpl,
                "RECIPE_API_URL", "https://api.edamam.com/api/recipes/v2?app_key={0}&app_id=ba324f9b&type=any");
        ReflectionTestUtils.setField(weatherServiceImpl,
                "RECIPE_API_KEY", "76a805351dc3865a68e48961ac856f19");

        String query = "https://api.edamam.com/api/recipes/v2?app_key=76a805351dc3865a68e48961ac856f19&app_id=ba324f9b&type=any&dishType=salad";

        // To read the json file to form the weatherApiObj
        WeatherApiObj weatherApiObj;
        Resource resource = new ClassPathResource("/good_weather_response.json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        weatherApiObj = objectMapper.readValue(resource.getInputStream(), WeatherApiObj.class);


        List<Weather> weathers = new ArrayList<>();
        Weather weather1 = new Weather(1L,-5, 15, 0,0,0,0,0,0,0,0,0,0,0,0);
        Weather weather2 = new Weather(2L,10, 15, 0,0,0,0,0,0,0,0,0,0,0,0);
        weathers.add(weather1);
        weathers.add(weather2);

        when(mockWeatherRepository.findByTemperatureBetweenTemperatureHighLow(weatherApiObj.getCurrentTemp())).thenReturn(weathers);

        List<FoodForWeather> foodForWeathers = new ArrayList<>();
        foodForWeathers.add(new FoodForWeather(1L, weather1, null, null, null, new DishType(1L, "salad"), null));
        when(mockFoodForWeatherRepository.findByWeatherIdIn(weathers)).thenReturn(foodForWeathers);

        String actualResult = weatherServiceImpl.buildExternalRecipeAPIQuery(weatherApiObj);

        assertEquals(query, actualResult);

        verify(mockWeatherRepository, times(1)).findByTemperatureBetweenTemperatureHighLow(weatherApiObj.getCurrentTemp());
        verify(mockFoodForWeatherRepository, times(1)).findByWeatherIdIn(weathers);
    }
}
