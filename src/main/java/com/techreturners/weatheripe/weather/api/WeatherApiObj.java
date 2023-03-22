package com.techreturners.weatheripe.weather.api;

import com.techreturners.weatheripe.external.dto.ResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class WeatherApiObj extends ResponseDTO {
    private WeatherTimelines timelines;

    public WeatherValues getCurrentValues(){
        List<WeatherMinutely> minutely = timelines.getMinutely();
        return minutely.size()>0?minutely.get(0).getValues():null;
    }

    public double getCurrentTemp(){
        return getCurrentValues()==null?0:getCurrentValues().getTemperature();
    }
}
