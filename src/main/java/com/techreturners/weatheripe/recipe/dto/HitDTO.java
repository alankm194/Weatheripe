package com.techreturners.weatheripe.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class HitDTO {

    RecipeDTO recipe;
}
