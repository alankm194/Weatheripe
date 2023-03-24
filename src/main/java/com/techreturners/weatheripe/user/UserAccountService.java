package com.techreturners.weatheripe.user;

import com.techreturners.weatheripe.exception.NoRecipeBookFoundException;
import com.techreturners.weatheripe.exception.NoRecipeFoundException;
import com.techreturners.weatheripe.external.dto.ResponseDTO;
import com.techreturners.weatheripe.model.RecipeBook;
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

    UserRecipeBookResponseDTO createUserRecipeBookResponseDTOs(List<RecipeBook> recipeBooks);
}
