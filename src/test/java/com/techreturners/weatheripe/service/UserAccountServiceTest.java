package com.techreturners.weatheripe.service;

import com.techreturners.weatheripe.exception.NoRecipeBookFoundException;
import com.techreturners.weatheripe.exception.UserSessionNotFoundException;
import com.techreturners.weatheripe.model.RecipeBook;
import com.techreturners.weatheripe.model.UserAccount;
import com.techreturners.weatheripe.repository.RecipeBookRepository;
import com.techreturners.weatheripe.repository.UserAccountRepository;
import com.techreturners.weatheripe.request.SignupRequest;
import com.techreturners.weatheripe.response.exception.UsernameAlreadyExistsException;
import com.techreturners.weatheripe.user.UserAccountServiceImpl;
import com.techreturners.weatheripe.user.dto.RecipeBookDTO;
import com.techreturners.weatheripe.user.dto.RecipeBookRequestDTO;
import com.techreturners.weatheripe.user.dto.UserRecipeBookDTO;
import com.techreturners.weatheripe.user.dto.UserRecipeBookResponseDTO;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
public class UserAccountServiceTest {

    @Mock
    private UserAccountRepository userAccountRepository;
    @Mock
    RecipeBookRepository recipeBookRepository;

    @InjectMocks
    UserAccountServiceImpl userAccountService ;


    @Test
    public void testUpdateRecipeBookEmptyRequest(){
        NoRecipeBookFoundException thrown = Assertions.assertThrows(NoRecipeBookFoundException.class, () ->
                userAccountService.updateRecipeBook(new RecipeBookRequestDTO(),"username")
        );
        assertEquals("No data found !" ,thrown.getMessage());

    }

    @Test
    public void testUpdateRecipeBookInvalidRecipeIds(){
        when(recipeBookRepository.findAllByRecipeIdIn(new ArrayList<Long> ())).thenReturn(new ArrayList<RecipeBook> ());
        RecipeBookDTO bookDTO1 = RecipeBookDTO.builder().recipeId(1L).rating(1d).isFavourite(true).build();
        RecipeBookDTO bookDTO2 = RecipeBookDTO.builder().recipeId(2L).rating(2.5d).isFavourite(false).build();
        RecipeBookRequestDTO recipeBookRequestDTO = RecipeBookRequestDTO.builder()
                .recipeBooks(new RecipeBookDTO[]{bookDTO1, bookDTO2}).build();

        NoRecipeBookFoundException thrown = Assertions.assertThrows(NoRecipeBookFoundException.class, () ->
                userAccountService.updateRecipeBook(recipeBookRequestDTO ,"username")
        );
        assertEquals("Requested recipe ids are not found system . Please check and try again." ,thrown.getMessage());

    }

    @Test
    public void testUpdateRecipeBook(){

        RecipeBookDTO bookDTO1 = RecipeBookDTO.builder().recipeId(1L).rating(1d).isFavourite(true).build();
        RecipeBookRequestDTO recipeBookRequestDTO = RecipeBookRequestDTO.builder()
                .recipeBooks(new RecipeBookDTO[]{bookDTO1}).build();

        RecipeBook recipeBook = RecipeBook.builder()
                .recipeURL("http://www.seriouseats.com/recipes/2016/08/iced-matcha-green-tea-recipe.html")
                .recipeName("Frothy Iced Matcha Green Tea Recipe")
                .calories(56.2D)
                .dishType("desserts")
                .userId(UserAccount.builder().build())
                .timestamp(java.time.LocalDateTime.now())
                .mealType("lunch/dinner")
                .cuisineType("american")
                .healthType("Vegan,Vegetarian,Pescatarian,Dairy-Free,Gluten-Free,Wheat-Free,Egg-Free")
                .dietType("High-Fiber,Low-Fat,Low-Sodium")
                .is_favourite(true)
                .rating(1)
                .recipeId(1L)
                .build();


        when(recipeBookRepository.findAllByRecipeIdIn(new ArrayList<Long> ())).thenReturn( List.of(recipeBook));
        when(recipeBookRepository.saveAllAndFlush(List.of(recipeBook))).thenReturn( List.of(recipeBook));
//        verify(recipeBookRepository, times(1)).saveAllAndFlush(List.of(recipeBook));
        assertEquals(bookDTO1.getIsFavourite(), recipeBook.getIs_favourite());
        assertEquals(bookDTO1.getRating(), recipeBook.getRating());

    }

    @Test
    public void testGetUserRecipeBooks(){
        UserAccount  userAccount = UserAccount.builder().userName("user")
                .email("abc@xyz.com")
                .password("340875##%4*/")
                .build();
        when(userAccountRepository.findByUserName("user")).thenReturn(Optional.ofNullable(userAccount));

        RecipeBook recipeBook = RecipeBook.builder()
                .recipeURL("http://www.seriouseats.com/recipes/2016/08/iced-matcha-green-tea-recipe.html")
                .recipeName("Frothy Iced Matcha Green Tea Recipe")
                .calories(56.2D)
                .dishType("desserts")
                .userId(UserAccount.builder().build())
                .timestamp(java.time.LocalDateTime.now())
                .mealType("lunch/dinner")
                .cuisineType("american")
                .healthType("Vegan,Vegetarian,Pescatarian,Dairy-Free,Gluten-Free,Wheat-Free,Egg-Free")
                .dietType("High-Fiber,Low-Fat,Low-Sodium")
                .is_favourite(true)
                .rating(1)
                .recipeId(1L)
                .build();
        when(recipeBookRepository.findByUserId(userAccount)).thenReturn(List.of(recipeBook));
        UserRecipeBookResponseDTO responseDTO =  userAccountService.createUserRecipeBookResponseDTOs(List.of(recipeBook));

        assertEquals(1,responseDTO.getUserRecipes().size());
    }

    @Test
    public void testGetUserRecipeBooksNoUser(){
        UserSessionNotFoundException thrown = Assertions.assertThrows(UserSessionNotFoundException.class, () ->
                userAccountService.getUserRecipeBooks(null)
        );
        assertEquals("User Session not found !" ,thrown.getMessage());

    }

}
