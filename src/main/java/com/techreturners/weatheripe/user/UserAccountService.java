package com.techreturners.weatheripe.user;

import com.techreturners.weatheripe.model.RecipeBook;
import com.techreturners.weatheripe.user.dto.UserRecipeBookResponseDTO;

import java.util.List;

public interface UserAccountService {

     UserRecipeBookResponseDTO saveUserRecipeBook(List<RecipeBook> recipeBook);
}
