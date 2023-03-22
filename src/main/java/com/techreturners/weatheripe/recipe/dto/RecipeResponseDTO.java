package com.techreturners.weatheripe.recipe.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techreturners.weatheripe.external.dto.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RecipeResponseDTO extends ResponseDTO{

    private HitDTO [] hits;
}
