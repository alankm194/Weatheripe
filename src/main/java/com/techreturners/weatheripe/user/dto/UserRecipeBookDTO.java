package com.techreturners.weatheripe.user.dto;

import com.techreturners.weatheripe.external.dto.ResponseDTO;
import jakarta.persistence.Column;
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
}
