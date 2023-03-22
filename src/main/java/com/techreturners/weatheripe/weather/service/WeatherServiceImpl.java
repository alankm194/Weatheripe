package com.techreturners.weatheripe.weather.service;

import com.techreturners.weatheripe.external.dto.ExternalRequestDto;
import com.techreturners.weatheripe.external.dto.ResponseDTO;
import com.techreturners.weatheripe.external.service.ExternalApiService;
import com.techreturners.weatheripe.weather.api.WeatherApiObj;
import com.techreturners.weatheripe.repository.WeatherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.MessageFormat;


@Slf4j
@Service
public class WeatherServiceImpl implements WeatherService{
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private ExternalApiService externalApiService;

    @Value("${weather.api.url}")
    private String WEATHER_API_URL;

    @Value("${weather.api.key}")
    private String WEATHER_API_KEY;


    public ResponseDTO getWeatherByLocation(String location){
        String uri = MessageFormat.format(WEATHER_API_URL,WEATHER_API_KEY, location);
        log.info("*******URI:"+uri);
        ExternalRequestDto externalRequestDto = new ExternalRequestDto(uri,new WeatherApiObj());
        WeatherApiObj weatherApiObj = (WeatherApiObj)externalApiService.getResourcesByUri(externalRequestDto);

        return weatherApiObj;
    }

}
