package com.techreturners.weatheripe.weather.dto;

import com.techreturners.weatheripe.external.dto.ResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class WeatherApiDTO extends ResponseDTO {
    private WeatherTimelinesDTO timelines;

    public WeatherValuesDTO getCurrentValues(){
        if (timelines!=null) {
            List<WeatherMinutelyDTO> minutely = timelines.getMinutely();
            return minutely.size() > 0 ? minutely.get(0).getValues() : null;
        }else{
            return null;
        }
    }

    public double getCurrentTemp(){
        return getCurrentValues()==null?0:getCurrentValues().getTemperature();
    }
}
