package com.techreturners.weatheripe.user.service;

import com.techreturners.weatheripe.exception.recipe.NoRecipeBookFoundException;
import com.techreturners.weatheripe.exception.recipe.NoRecipeFoundException;
import com.techreturners.weatheripe.external.dto.ResponseDTO;
import com.techreturners.weatheripe.model.recipe.RecipeBook;
import com.techreturners.weatheripe.user.dto.RecipeBookRequestDTO;
import com.techreturners.weatheripe.user.dto.UserRecipeBookResponseDTO;

import java.util.List;

public interface UserAccountService {

    UserRecipeBookResponseDTO saveUserRecipeBook(List<RecipeBook> recipeBook);

    ResponseDTO getUserRecipeBooks(String token) throws NoRecipeFoundException;

    ResponseDTO updateRecipeBook(RecipeBookRequestDTO recipeBookRequestDTO, String sub) throws NoRecipeBookFoundException;

    void deleteUserById(Long userId);

    void deleteUserByUsername(String username);

    void deleteRecipeBook(Long recipeBookId, String sub);
}
