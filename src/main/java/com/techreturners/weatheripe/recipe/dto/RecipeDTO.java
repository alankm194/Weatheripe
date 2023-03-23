package com.techreturners.weatheripe.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class RecipeDTO {

    String url;
    String uri;
    String label;
    String calories;
    String totalWeight;
    String [] healthLabels;
    String [] cuisineType;
    String [] mealType;
    String [] dishType;
}
