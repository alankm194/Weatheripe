package com.techreturners.weatheripe.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRecipeBookDTO {

    Long recipeBookId;
    String recipe;
    String url;
    String dishType;
    String dietType;
    String healthType;
    String mealType;
    String cuisineType;
    double calories;
    double rating;
    boolean isFavourite;
}
