package com.techreturners.weatheripe.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecipeBookDTO {

    long recipeId;
    Double rating ;
    Boolean isFavourite;
}
