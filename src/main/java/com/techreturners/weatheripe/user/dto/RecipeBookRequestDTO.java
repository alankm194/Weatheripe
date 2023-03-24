package com.techreturners.weatheripe.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeBookRequestDTO {

    RecipeBookDTO[] recipeBooks;

}
