package com.techreturners.weatheripe.weather.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.weatheripe.configuration.SecretConfiguration;
import com.techreturners.weatheripe.exception.recipe.NoMatchingFoodException;
import com.techreturners.weatheripe.exception.weather.NoMatchingWeatherException;
import com.techreturners.weatheripe.exception.weather.WeatherNotFoundException;
import com.techreturners.weatheripe.weather.dto.RecipeQueryDTO;
import com.techreturners.weatheripe.model.recipe.DishType;
import com.techreturners.weatheripe.model.FoodForWeather;
import com.techreturners.weatheripe.model.weather.Weather;
import com.techreturners.weatheripe.repository.FoodForWeatherRepository;
import com.techreturners.weatheripe.repository.WeatherRepository;
import com.techreturners.weatheripe.weather.dto.WeatherApiDTO;
import org.junit.jupiter.api.BeforeEach;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DataJpaTest
public class WeatherServiceTests {

    @Mock
    private WeatherRepository mockWeatherRepository;

    @Mock
    private FoodForWeatherRepository mockFoodForWeatherRepository;

    @Mock
    private SecretConfiguration secretConfiguration;
    @InjectMocks
    private WeatherServiceImpl weatherServiceImpl;

    @BeforeEach
    public void init() {
        when(secretConfiguration.recipeAppId()).thenReturn("dummyAppId");
        when(secretConfiguration.recipeAppKey()).thenReturn("dummyAppKey");
        ReflectionTestUtils.setField(weatherServiceImpl,
                "RECIPE_API_URL", "https://api.edamam.com/api/recipes/v2?app_key={0}&app_id={1}&type=any");
    }


    @Test
    public void testBuildExternalRecipeAPIQueryReturnQueryString() throws Exception {

        when(secretConfiguration.recipeAppId()).thenReturn("dummyAppId");
        when(secretConfiguration.recipeAppKey()).thenReturn("dummyAppKey");
        String query = "https://api.edamam.com/api/recipes/v2?app_key=dummyAppKey&app_id=dummyAppId&type=any&dishType=salad";

        // To read the json file to form the weatherApiObj
        Resource resource = new ClassPathResource("/good_weather_response.json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        WeatherApiDTO weatherApiObj = objectMapper.readValue(resource.getInputStream(), WeatherApiDTO.class);


        List<Weather> weathers = new ArrayList<>();
        Weather weather1 = new Weather(1L, -5, 15, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0);
        Weather weather2 = new Weather(2L, 10, 15, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0);
        weathers.add(weather1);
        weathers.add(weather2);
        when(mockWeatherRepository.findByTemperatureBetweenTemperatureHighLow(weatherApiObj.getCurrentTemp()))
                .thenReturn(weathers);

        List<FoodForWeather> foodForWeathers = new ArrayList<>();
        foodForWeathers.add(new FoodForWeather(1L, weather1, null, null,
                null, new DishType(1L, "salad"), null));
        when(mockFoodForWeatherRepository.findByWeatherIdIn(weathers)).thenReturn(foodForWeathers);

        RecipeQueryDTO recipeQueryDTO = (RecipeQueryDTO) weatherServiceImpl.buildExternalRecipeAPIQuery(weatherApiObj);
        assertEquals(query, recipeQueryDTO.getQuery());

        verify(mockWeatherRepository, times(1))
                .findByTemperatureBetweenTemperatureHighLow(weatherApiObj.getCurrentTemp());
        verify(mockFoodForWeatherRepository, times(1)).findByWeatherIdIn(weathers);
    }

    @Test
    public void testBuildExternalRecipeAPIQueryThrowExceptionInGettingExternalResponse() throws Exception {
        String query = "https://api.edamam.com/api/recipes/v2?app_key=dummyAppKey&app_id=dummyAppId&type=any&dishType=salad";

        // To read the json file to form the weatherApiObj
        Resource resource = new ClassPathResource("/bad_weather_response.json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        WeatherApiDTO weatherApiObj = objectMapper.readValue(resource.getInputStream(), WeatherApiDTO.class);

        Exception exception = assertThrows(WeatherNotFoundException.class,
                () -> weatherServiceImpl.buildExternalRecipeAPIQuery(weatherApiObj));

        verify(mockWeatherRepository, times(0))
                .findByTemperatureBetweenTemperatureHighLow(weatherApiObj.getCurrentTemp());
        verify(mockFoodForWeatherRepository, times(0)).findByWeatherIdIn(any());
    }

    @Test
    public void testBuildExternalRecipeAPIQueryThrowExceptionInFindWeatherModel() throws Exception {
        String query = "https://api.edamam.com/api/recipes/v2?app_key=dummyAppKey&app_id=dummyAppId&type=any&dishType=salad";

        // To read the json file to form the weatherApiObj
        Resource resource = new ClassPathResource("/good_weather_response.json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        WeatherApiDTO weatherApiObj = objectMapper.readValue(resource.getInputStream(), WeatherApiDTO.class);

        when(mockWeatherRepository.findByTemperatureBetweenTemperatureHighLow(weatherApiObj.getCurrentTemp()))
                .thenReturn(new ArrayList<>());

        Exception exception = assertThrows(NoMatchingWeatherException.class,
                () -> weatherServiceImpl.buildExternalRecipeAPIQuery(weatherApiObj));

        verify(mockWeatherRepository, times(1))
                .findByTemperatureBetweenTemperatureHighLow(weatherApiObj.getCurrentTemp());
        verify(mockFoodForWeatherRepository, times(0)).findByWeatherIdIn(any());
    }

    @Test
    public void testBuildExternalRecipeAPIQueryThrowExceptionInFindFoodTypeModel() throws Exception {
        String query = "https://api.edamam.com/api/recipes/v2?app_key=dummyAppKey&app_id=dummyAppId&type=any&dishType=salad";

        // To read the json file to form the weatherApiObj
        Resource resource = new ClassPathResource("/good_weather_response.json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        WeatherApiDTO weatherApiObj = objectMapper.readValue(resource.getInputStream(), WeatherApiDTO.class);

        List<Weather> weathers = new ArrayList<>();
        Weather weather1 = new Weather(1L, -5, 15, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0);
        Weather weather2 = new Weather(2L, 10, 15, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0);
        weathers.add(weather1);
        weathers.add(weather2);
        when(mockWeatherRepository.findByTemperatureBetweenTemperatureHighLow(weatherApiObj.getCurrentTemp()))
                .thenReturn(weathers);

        when(mockFoodForWeatherRepository.findByWeatherIdIn(weathers)).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(NoMatchingFoodException.class,
                () -> weatherServiceImpl.buildExternalRecipeAPIQuery(weatherApiObj));

        verify(mockWeatherRepository, times(1))
                .findByTemperatureBetweenTemperatureHighLow(weatherApiObj.getCurrentTemp());
        verify(mockFoodForWeatherRepository, times(1)).findByWeatherIdIn(any());
    }
}
