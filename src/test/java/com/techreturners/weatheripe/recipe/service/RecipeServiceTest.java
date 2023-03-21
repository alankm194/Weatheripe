package com.techreturners.weatheripe.recipe.service;

import com.techreturners.weatheripe.external.service.ExternalApiService;
import com.techreturners.weatheripe.external.service.ExternalApiServiceImpl;
import com.techreturners.weatheripe.model.DishType;
import com.techreturners.weatheripe.model.FoodForWeather;
import com.techreturners.weatheripe.model.Weather;
import com.techreturners.weatheripe.recipe.dto.RecipeResponseDTO;
import com.techreturners.weatheripe.repository.FoodForWeatherRepository;
import com.techreturners.weatheripe.repository.WeatherRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
public class RecipeServiceTest {

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Mock
    private ExternalApiServiceImpl externalApiService;

    @Mock
    private FoodForWeatherRepository mockFoodForWeatherRepository;
    @Mock
    private WeatherRepository WeatherRepository;


    @Test
    public void getRecipeByWeatherCondition() {
        masterDataSetup() ;
        Long foodForWeatherId = 1L;
        FoodForWeather foodForWeather = new FoodForWeather(35L, new Weather(), null, null, null, new DishType(35L, "Soup"), null);
        when(mockFoodForWeatherRepository.findById(foodForWeatherId)).thenReturn(Optional.of(foodForWeather));

        RecipeResponseDTO recipeResponseDTO = (RecipeResponseDTO) recipeService.getRecipeByWeatherCondition(foodForWeather);
        assertThat(recipeResponseDTO).isNull();

    }


    public void masterDataSetup() {
        double[][] temperatureArray = new double[][]{{-4, 0}, {0, 4}, {4, 8}, {9, 15}};

        List<Weather> weatherList = new ArrayList<>();
        Arrays.stream(temperatureArray)
                .map(temperature -> new Weather(null, temperature[0], temperature[1], 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d))
                .forEach(weatherList::add);

        WeatherRepository.saveAll(weatherList);


        assertThat(WeatherRepository.findAll().size()).isEqualTo(weatherList.size());
    }


}
