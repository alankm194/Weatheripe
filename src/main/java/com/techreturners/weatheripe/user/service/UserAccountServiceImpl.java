package com.techreturners.weatheripe.user.service;

import com.techreturners.weatheripe.exception.*;
import com.techreturners.weatheripe.exception.recipe.NoRecipeBookFoundException;
import com.techreturners.weatheripe.exception.recipe.NoRecipeFoundException;
import com.techreturners.weatheripe.exception.recipe.RecipeNotBelongToUserException;
import com.techreturners.weatheripe.exception.userauthentication.UserNotFoundException;
import com.techreturners.weatheripe.exception.userauthentication.UserSessionNotFoundException;
import com.techreturners.weatheripe.external.dto.ResponseDTO;
import com.techreturners.weatheripe.model.recipe.RecipeBook;
import com.techreturners.weatheripe.model.user.UserAccount;
import com.techreturners.weatheripe.repository.RecipeBookRepository;
import com.techreturners.weatheripe.repository.UserAccountRepository;
import com.techreturners.weatheripe.user.dto.RecipeBookDTO;
import com.techreturners.weatheripe.user.dto.RecipeBookRequestDTO;
import com.techreturners.weatheripe.user.dto.UserRecipeBookDTO;
import com.techreturners.weatheripe.user.dto.UserRecipeBookResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Slf4j
@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    RecipeBookRepository recipeBookRepository;
    @Autowired
    UserAccountRepository userAccountRepository;

    public UserRecipeBookResponseDTO saveUserRecipeBook(List<RecipeBook> recipeBooks) {
        recipeBookRepository.saveAllAndFlush(recipeBooks);
        return createUserRecipeBookResponseDTOs(recipeBooks);

    }

    @Override
    public ResponseDTO getUserRecipeBooks(String userToken) throws NoRecipeFoundException {
        Optional<UserAccount> account = userAccountRepository.findByUserName(userToken);
        if (account.isEmpty()) {
            throw new UserSessionNotFoundException(ExceptionMessages.USER_SESSION_NOT_FOUND);
        }
        List<RecipeBook> recipeBooks = recipeBookRepository.findByUserId(account.get());

        if (recipeBooks.isEmpty()) {
            throw new NoRecipeFoundException(ExceptionMessages.NO_RECIPE_FOUND);
        }

        return createUserRecipeBookResponseDTOs(recipeBooks);
    }

    @Override
    public ResponseDTO updateRecipeBook(RecipeBookRequestDTO recipeBookRequestDTO, String sub) throws NoRecipeBookFoundException {
        if (recipeBookRequestDTO.getRecipeBooks() == null || recipeBookRequestDTO.getRecipeBooks().length < 1) {
            throw new NoRecipeBookFoundException(ExceptionMessages.NO_RECIPE_IDS);//no ids in requests
        }
        List<Long> recipeIds = Arrays.stream(recipeBookRequestDTO.getRecipeBooks())
                .map(RecipeBookDTO::getRecipeId).toList();
        if (recipeIds.isEmpty()) {
            throw new NoRecipeBookFoundException(ExceptionMessages.NO_RECIPE_IDS);//no ids in requests
        }
        List<RecipeBook> recipeBooks = recipeBookRepository.findAllByRecipeIdIn(recipeIds);
        if (recipeBooks.isEmpty()) {
            throw new NoRecipeBookFoundException(ExceptionMessages.INVALID_RECIPE_IDS);//invalid ids
        }
        Map<Long, RecipeBook> recipeBooksById = recipeBooks.stream()
                .collect(Collectors.toMap(RecipeBook::getRecipeId, Function.identity()));

        List<RecipeBook> updateList = new ArrayList<>();
        for (RecipeBookDTO dto : recipeBookRequestDTO.getRecipeBooks()) {
            RecipeBook recipeBook = recipeBooksById.get(dto.getRecipeId());
            if (dto.getRating() != null) {
                recipeBook.setRating(dto.getRating());
            }
            if (dto.getIsFavourite() != null) {
                recipeBook.setIs_favourite(dto.getIsFavourite());
            }
            recipeBook.setTimestamp(java.time.LocalDateTime.now());
            updateList.add(recipeBook);
        }

        recipeBookRepository.saveAllAndFlush(updateList);
        return createUserRecipeBookResponseDTOs(updateList);

    }


    public UserRecipeBookResponseDTO createUserRecipeBookResponseDTOs(List<RecipeBook> recipeBooks){
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
                                .isFavourite(recipeBook.getIs_favourite())
                                .build())
                .forEach(recipeBookDTOList::add);
        return new UserRecipeBookResponseDTO(recipeBookDTOList);
    }

    public void deleteRecipeBook(Long recipeBookId, String username){
        UserAccount userAccount = userAccountRepository.findByUserName(username)
                .orElseThrow(() -> new UserSessionNotFoundException(ExceptionMessages.USER_SESSION_NOT_FOUND));

        Optional<RecipeBook> recipeBookOptional = recipeBookRepository.findById(recipeBookId);

        if (recipeBookOptional.isEmpty())
            throw new NoRecipeBookFoundException(ExceptionMessages.NO_RECIPE_FOUND);

        log.debug("******recipeBookOptional.get().getUserId():"+recipeBookOptional.get().getUserId());
        log.debug("******userAccount.getId():"+userAccount.getId());
        if (!recipeBookOptional.get().getUserId().getId().equals(userAccount.getId())){
            throw new RecipeNotBelongToUserException(ExceptionMessages.RECIPE_NOT_BELONG_TO_USER);
        }

        recipeBookRepository.delete(recipeBookOptional.get());
    }

    public void deleteUserByUsername(String username){
        log.debug("******deleteUserByUsername("+username+"):");

        UserAccount userAccount = userAccountRepository.findByUserName(username)
                .orElseThrow(() -> new UserSessionNotFoundException(ExceptionMessages.USER_SESSION_NOT_FOUND));
        userAccountRepository.deleteById(userAccount.getId());
    }
}
