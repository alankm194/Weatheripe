package com.techreturners.weatheripe.weather.dto;

import com.techreturners.weatheripe.external.dto.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RecipeQueryDTO extends ResponseDTO {
    private String query;

}
