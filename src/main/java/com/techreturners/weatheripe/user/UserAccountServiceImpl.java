package com.techreturners.weatheripe.user;

import com.techreturners.weatheripe.exception.ExceptionMessages;
import com.techreturners.weatheripe.exception.NoRecipeFoundException;
import com.techreturners.weatheripe.exception.UserSessionNotFoundException;
import com.techreturners.weatheripe.external.dto.ResponseDTO;
import com.techreturners.weatheripe.model.RecipeBook;
import com.techreturners.weatheripe.model.UserAccount;
import com.techreturners.weatheripe.repository.RecipeBookRepository;
import com.techreturners.weatheripe.repository.UserAccountRepository;
import com.techreturners.weatheripe.user.dto.UserRecipeBookDTO;
import com.techreturners.weatheripe.user.dto.UserRecipeBookResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    RecipeBookRepository recipeBookRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;

    public UserRecipeBookResponseDTO saveUserRecipeBook(List<RecipeBook> recipeBooks) {
        recipeBookRepository.saveAllAndFlush(recipeBooks);
        List<UserRecipeBookDTO> userRecipeBookDTOS = new ArrayList<>();

        new UserRecipeBookDTO();
        recipeBooks.stream()
                .map(recipeBook ->
                        UserRecipeBookDTO.builder()
                                .recipeBookId(recipeBook.getRecipeId())
                                .recipe(recipeBook.getRecipeName())
                                .url(recipeBook.getRecipeURL())
                                .dishType(recipeBook.getDishType())
                                .cuisineType(recipeBook.getCuisineType())
                                .healthType(recipeBook.getHealthType())
                                .mealType(recipeBook.getMealType())
                                .rating(recipeBook.getRating())
                                .dietType(recipeBook.getDietType())
                                .calories(recipeBook.getCalories())
                                .build())
                .forEach(userRecipeBookDTOS::add);

        return new UserRecipeBookResponseDTO(userRecipeBookDTOS);
    }

    @Override
    public ResponseDTO getUserRecipeBooks(String userToken) throws NoRecipeFoundException {
        Optional<UserAccount> account = userAccountRepository.findByUserName(userToken);
        if (account.isEmpty()) {
            throw new UserSessionNotFoundException(ExceptionMessages.USER_SESSION_NOT_FOUND);
        }
        List<RecipeBook> recipeBooks = recipeBookRepository.findByUserId(account.get());

        if(recipeBooks.isEmpty()){
          throw new NoRecipeFoundException(ExceptionMessages.NO_RECIPE_FOUND);
        }

        List<UserRecipeBookDTO> recipeBookDTOList = new ArrayList<>();

        new UserRecipeBookDTO();
        recipeBooks.stream()
                .map(recipeBook ->
                        UserRecipeBookDTO.builder()
                                .recipeBookId(recipeBook.getRecipeId())
                                .recipe(recipeBook.getRecipeName())
                                .url(recipeBook.getRecipeURL())
                                .dishType(recipeBook.getDishType())
                                .cuisineType(recipeBook.getCuisineType())
                                .healthType(recipeBook.getHealthType())
                                .mealType(recipeBook.getMealType())
                                .rating(recipeBook.getRating())
                                .dietType(recipeBook.getDietType())
                                .calories(recipeBook.getCalories())
                                .build())
                .forEach(recipeBookDTOList::add);
        return  new UserRecipeBookResponseDTO(recipeBookDTOList);
    }
}
