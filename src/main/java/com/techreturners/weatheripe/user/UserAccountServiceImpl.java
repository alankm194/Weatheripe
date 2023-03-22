package com.techreturners.weatheripe.user;

import com.techreturners.weatheripe.model.RecipeBook;
import com.techreturners.weatheripe.repository.RecipeBookRepository;
import com.techreturners.weatheripe.user.dto.UserRecipeBookDTO;
import com.techreturners.weatheripe.user.dto.UserRecipeBookResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    RecipeBookRepository recipeBookRepository;


    public UserRecipeBookResponseDTO saveUserRecipeBook(List<RecipeBook> recipeBooks) {
        recipeBookRepository.saveAllAndFlush(recipeBooks);
        List<UserRecipeBookDTO> userRecipeBookDTOS = new ArrayList<>();

        recipeBooks.stream()
                .map(recipeBook -> new UserRecipeBookDTO(recipeBook.getRecipeId(), recipeBook.getRecipeName(), recipeBook.getRecipeURL()))
                .forEach(userRecipeBookDTOS::add);

        return new UserRecipeBookResponseDTO(userRecipeBookDTOS);
    }


}
