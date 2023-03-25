package com.techreturners.weatheripe.service;

import com.techreturners.weatheripe.exception.ExceptionMessages;
import com.techreturners.weatheripe.exception.recipe.NoRecipeBookFoundException;
import com.techreturners.weatheripe.exception.recipe.RecipeNotBelongToUserException;
import com.techreturners.weatheripe.exception.userauthentication.UserNotFoundException;
import com.techreturners.weatheripe.exception.userauthentication.UserSessionNotFoundException;
import com.techreturners.weatheripe.model.recipe.RecipeBook;
import com.techreturners.weatheripe.model.user.UserAccount;
import com.techreturners.weatheripe.repository.RecipeBookRepository;
import com.techreturners.weatheripe.repository.UserAccountRepository;
import com.techreturners.weatheripe.user.dto.RecipeBookDTO;
import com.techreturners.weatheripe.user.dto.RecipeBookRequestDTO;
import com.techreturners.weatheripe.user.dto.UserRecipeBookResponseDTO;
import com.techreturners.weatheripe.user.service.UserAccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DataJpaTest
public class UserAccountServiceTest {

    @Mock
    private UserAccountRepository userAccountRepository;
    @Mock
    RecipeBookRepository recipeBookRepository;

    @InjectMocks
    UserAccountServiceImpl userAccountService;

    @Test
    public void testUpdateRecipeBookEmptyRequest() {
        NoRecipeBookFoundException thrown = Assertions.assertThrows(NoRecipeBookFoundException.class, () ->
                userAccountService.updateRecipeBook(new RecipeBookRequestDTO(), "username")
        );
        assertEquals("No data found !", thrown.getMessage());

    }

    @Test
    public void testUpdateRecipeBookInvalidRecipeIds() {
        when(recipeBookRepository.findAllByRecipeIdIn(new ArrayList<Long>())).thenReturn(new ArrayList<RecipeBook>());
        RecipeBookDTO bookDTO1 = RecipeBookDTO.builder().recipeId(1L).rating(1d).isFavourite(true).build();
        RecipeBookDTO bookDTO2 = RecipeBookDTO.builder().recipeId(2L).rating(2.5d).isFavourite(false).build();
        RecipeBookRequestDTO recipeBookRequestDTO = RecipeBookRequestDTO.builder()
                .recipeBooks(new RecipeBookDTO[]{bookDTO1, bookDTO2}).build();

        NoRecipeBookFoundException thrown = Assertions.assertThrows(NoRecipeBookFoundException.class, () ->
                userAccountService.updateRecipeBook(recipeBookRequestDTO, "username")
        );
        assertEquals("Requested recipe ids are not found system . Please check and try again.", thrown.getMessage());

    }

    @Test
    public void testUpdateRecipeBook() {

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


        when(recipeBookRepository.findAllByRecipeIdIn(new ArrayList<Long>())).thenReturn(List.of(recipeBook));
        when(recipeBookRepository.saveAllAndFlush(List.of(recipeBook))).thenReturn(List.of(recipeBook));
//        verify(recipeBookRepository, times(1)).saveAllAndFlush(List.of(recipeBook));
        assertEquals(bookDTO1.getIsFavourite(), recipeBook.getIs_favourite());
        assertEquals(bookDTO1.getRating(), recipeBook.getRating());

    }

    @Test
    public void testGetUserRecipeBooks() {
        UserAccount userAccount = UserAccount.builder().userName("user")
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
        UserRecipeBookResponseDTO responseDTO = userAccountService.createUserRecipeBookResponseDTOs(List.of(recipeBook));

        assertEquals(1, responseDTO.getUserRecipes().size());
    }

    @Test
    public void testGetUserRecipeBooksNoUser() {
        UserSessionNotFoundException thrown = Assertions.assertThrows(UserSessionNotFoundException.class, () ->
                userAccountService.getUserRecipeBooks(null)
        );
        assertEquals("User Session not found !", thrown.getMessage());

    }

    @Test
    public void testDeleteRecipeBookReturnSuccess() throws Exception {
        String username = "";
        UserAccount userAccount = UserAccount.builder()
                .id(1L)
                .userName(username)
                .password("12345678")
                .email("1234@gmail.com")
                .build();

        Long recipeBookId = 1L;
        when(userAccountRepository.findByUserName(username))
                .thenReturn(Optional.of(userAccount));

        RecipeBook recipeBook = RecipeBook.builder()
                .userId(userAccount)
                .recipeId(recipeBookId)
                .build();
        when(recipeBookRepository.findById(recipeBookId)).thenReturn(Optional.of(recipeBook));

        doNothing().when(recipeBookRepository).delete(recipeBook);

        userAccountService.deleteRecipeBook(recipeBookId, username);

        verify(userAccountRepository, times(1))
                .findByUserName(username);
        verify(recipeBookRepository, times(1)).findById(recipeBookId);
        verify(recipeBookRepository, times(1)).delete(recipeBook);
    }

    @Test
    public void testDeleteRecipeBookReturnFailOnUserNotFound() throws Exception {
        String username = "";
        UserAccount userAccount = UserAccount.builder()
                .id(1L)
                .userName(username)
                .password("12345678")
                .email("1234@gmail.com")
                .build();

        Long recipeBookId = 1L;

        doThrow(new UserSessionNotFoundException(ExceptionMessages.USER_SESSION_NOT_FOUND))
                .when(userAccountRepository).findByUserName(username);

        RecipeBook recipeBook = RecipeBook.builder()
                .userId(userAccount)
                .recipeId(recipeBookId)
                .build();

        Exception exception = assertThrows(UserSessionNotFoundException.class,
                () -> userAccountService.deleteRecipeBook(recipeBookId, username));

        String actualMessage = exception.getMessage();
        assertEquals(actualMessage, ExceptionMessages.USER_SESSION_NOT_FOUND);


        verify(userAccountRepository, times(1))
                .findByUserName(username);
        verify(recipeBookRepository, times(0)).findById(recipeBookId);
        verify(recipeBookRepository, times(0)).delete(recipeBook);
    }

    @Test
    public void testDeleteRecipeBookReturnFailOnRecipeBookNotFound() throws Exception {
        String username = "";
        UserAccount userAccount = UserAccount.builder()
                .id(1L)
                .userName(username)
                .password("12345678")
                .email("1234@gmail.com")
                .build();

        Long recipeBookId = 1L;
        when(userAccountRepository.findByUserName(username))
                .thenReturn(Optional.of(userAccount));

        RecipeBook recipeBook = RecipeBook.builder()
                .userId(userAccount)
                .recipeId(recipeBookId)
                .build();

        doThrow(new NoRecipeBookFoundException(ExceptionMessages.NO_RECIPE_FOUND))
                .when(recipeBookRepository).findById(recipeBookId);

        Exception exception = assertThrows(NoRecipeBookFoundException.class,
                () -> userAccountService.deleteRecipeBook(recipeBookId, username));

        String actualMessage = exception.getMessage();
        assertEquals(actualMessage, ExceptionMessages.NO_RECIPE_FOUND);


        verify(userAccountRepository, times(1))
                .findByUserName(username);
        verify(recipeBookRepository, times(1)).findById(recipeBookId);
        verify(recipeBookRepository, times(0)).delete(recipeBook);
    }

    @Test
    public void testDeleteRecipeBookReturnFailOnRecipeNotBelongToUser() throws Exception {
        String username = "user1";
        String username2 = "user2";
        UserAccount userAccount = UserAccount.builder()
                .id(1L)
                .userName(username)
                .password("12345678")
                .email("user1@gmail.com")
                .build();

        UserAccount userAccount2 = UserAccount.builder()
                .id(2L)
                .userName(username2)
                .password("12345699")
                .email("user2@gmail.com")
                .build();

        Long recipeBookId = 1L;
        when(userAccountRepository.findByUserName(username))
                .thenReturn(Optional.of(userAccount));

        RecipeBook recipeBook = RecipeBook.builder()
                .userId(userAccount2)
                .recipeId(recipeBookId)
                .build();
        when(recipeBookRepository.findById(recipeBookId)).thenReturn(Optional.of(recipeBook));

        doNothing().when(recipeBookRepository).delete(recipeBook);

        Exception exception = assertThrows(RecipeNotBelongToUserException.class,
                () -> userAccountService.deleteRecipeBook(recipeBookId, username));

        String actualMessage = exception.getMessage();
        assertEquals(actualMessage, ExceptionMessages.RECIPE_NOT_BELONG_TO_USER);

        verify(userAccountRepository, times(1))
                .findByUserName(username);
        verify(recipeBookRepository, times(1)).findById(recipeBookId);
        verify(recipeBookRepository, times(0)).delete(recipeBook);
    }

    @Test
    public void testDeleteUserByUsernameReturnSuccess() throws Exception {
        String username = "";
        UserAccount userAccount = UserAccount.builder()
                .id(1L)
                .userName(username)
                .password("12345678")
                .email("1234@gmail.com")
                .build();

        when(userAccountRepository.findByUserName(username)).thenReturn(Optional.of(userAccount));
        doNothing().when(userAccountRepository).deleteById(userAccount.getId());

        userAccountService.deleteUserByUsername(username);

        verify(userAccountRepository, times(1))
                .findByUserName(username);
        verify(userAccountRepository, times(1)).deleteById(userAccount.getId());
    }

    @Test
    public void testDeleteUserByUsernameReturnFailOnUserAccountNotFound() throws Exception {
        String username = "";
        UserAccount userAccount = UserAccount.builder()
                .id(1L)
                .userName(username)
                .password("12345678")
                .email("1234@gmail.com")
                .build();

        doThrow(new UserSessionNotFoundException(ExceptionMessages.USER_SESSION_NOT_FOUND))
                .when(userAccountRepository).findByUserName(username);

        Exception exception = assertThrows(UserSessionNotFoundException.class,
                () -> userAccountService.deleteUserByUsername(username));

        String actualMessage = exception.getMessage();
        assertEquals(actualMessage, ExceptionMessages.USER_SESSION_NOT_FOUND);

        verify(userAccountRepository, times(1))
                .findByUserName(username);
        verify(userAccountRepository, times(0)).deleteById(userAccount.getId());
    }


    @Test
    public void testDeleteUserByIdReturnSuccess() throws Exception {
        String username = "";
        Long userId = 1L;
        UserAccount userAccount = UserAccount.builder()
                .id(userId)
                .userName(username)
                .password("12345678")
                .email("1234@gmail.com")
                .build();

        when(userAccountRepository.existsById(userId)).thenReturn(true);
        doNothing().when(userAccountRepository).deleteById(userAccount.getId());

        userAccountService.deleteUserById(userId);

        verify(userAccountRepository, times(1))
                .existsById(userId);
        verify(userAccountRepository, times(1)).deleteById(userAccount.getId());
    }

    @Test
    public void testDeleteUserByIdReturnFailOnUserAccountNotFound() throws Exception {
        String username = "";
        Long userId = 1L;
        UserAccount userAccount = UserAccount.builder()
                .id(userId)
                .userName(username)
                .password("12345678")
                .email("1234@gmail.com")
                .build();

        when(userAccountRepository.existsById(userId)).thenReturn(false);

        Exception exception = assertThrows(UserNotFoundException.class,
                () -> userAccountService.deleteUserById(userId));

        String actualMessage = exception.getMessage();
        assertEquals(actualMessage, ExceptionMessages.USER_ACCOUNT_NOT_FOUND);

        verify(userAccountRepository, times(1))
                .existsById(userId);
        verify(userAccountRepository, times(0)).deleteById(userAccount.getId());
    }


}
