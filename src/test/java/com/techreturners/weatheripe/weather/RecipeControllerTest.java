package com.techreturners.weatheripe.weather;

import com.techreturners.weatheripe.external.dto.ExternalRequestDto;
import com.techreturners.weatheripe.external.dto.ResponseDTO;
import com.techreturners.weatheripe.weather.api.WeatherApiObj;
import com.techreturners.weatheripe.weather.api.WeatherMinutely;
import com.techreturners.weatheripe.weather.api.WeatherTimelines;
import com.techreturners.weatheripe.weather.api.WeatherValues;
import com.techreturners.weatheripe.weather.service.WeatherServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RecipeControllerTest {

    @Mock
    WeatherServiceImpl weatherService;

    @InjectMocks
    RecipeController recipeController;


    @Test
    void testGetWeatherByLocation() {

        // set up url & key in weather service
        String url = new String("https://api.tomorrow.io/v4/weather/forecast?apikey={0}&location={1}");
        ReflectionTestUtils.setField(weatherService, "WEATHER_API_URL", url);

        String key = new String("1ASbr2RuShHDO3HMhwGwbICyplG4UZ00");
        ReflectionTestUtils.setField(weatherService, "WEATHER_API_KEY", key);

        // begin to create data we want returned from mocked call
        WeatherValues weatherValues = new WeatherValues();
        double curTempterature = 10.1d;
        weatherValues.setTemperature(curTempterature);

        WeatherMinutely weatherMinutely = new WeatherMinutely();
        LocalDateTime curTime = LocalDateTime.now();
        weatherMinutely.setTime(curTime);
        weatherMinutely.setValues(weatherValues);

        List<WeatherMinutely> weatherMinutelyList = new ArrayList<WeatherMinutely>();
        weatherMinutelyList.add(weatherMinutely);

        WeatherTimelines weatherTimelines = new WeatherTimelines();
        weatherTimelines.setMinutely(weatherMinutelyList);

        WeatherApiObj weatherApiObj = new WeatherApiObj();
        weatherApiObj.setTimelines(weatherTimelines);

        ResponseDTO tempRet = (ResponseDTO) weatherApiObj;

        ExternalRequestDto externalRequestDto = new ExternalRequestDto("url", new WeatherApiObj());
        when(weatherService.getWeatherByLocation("London")).thenReturn(tempRet);

        // call the api we want to test - should call mocked method & return above data
        ResponseEntity<ResponseDTO> responseDTOOut = recipeController.getWeatherByLocation("London");

        // check
        HttpStatusCode isOk = responseDTOOut.getStatusCode();

        WeatherApiObj body = (WeatherApiObj)responseDTOOut.getBody();
        WeatherTimelines retTimeLInes = body.getTimelines();
        List<WeatherMinutely> retMinutely = retTimeLInes.getMinutely();

        assertThat(retMinutely.size()==1);
        assertThat(retMinutely.get(0).getTime() == curTime);
        assertThat(retMinutely.get(0).getValues().getTemperature() == curTempterature);

    }

}