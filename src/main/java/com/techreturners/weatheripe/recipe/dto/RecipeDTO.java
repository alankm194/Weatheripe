package com.techreturners.weatheripe.recipe.dto;

import lombok.Data;

@Data
public class RecipeDTO {

    String uri;
    String label;
    String calories;
    String totalWeight;
    String [] healthLabels;
    String [] cuisineType;
    String [] mealType;
    String [] dishType;
}
