package com.techreturners.weatheripe.user;

import com.techreturners.weatheripe.exception.ExceptionMessages;
import com.techreturners.weatheripe.exception.recipe.NoRecipeBookFoundException;
import com.techreturners.weatheripe.exception.recipe.RecipeNotBelongToUserException;
import com.techreturners.weatheripe.exception.userauthentication.UserNotFoundException;
import com.techreturners.weatheripe.model.recipe.RecipeBook;
import com.techreturners.weatheripe.model.user.UserAccount;
import com.techreturners.weatheripe.repository.RecipeBookRepository;
import com.techreturners.weatheripe.repository.UserAccountRepository;
import com.techreturners.weatheripe.user.service.UserAccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DataJpaTest
public class UserAccountServiceTest {
    @Mock
    private RecipeBookRepository mockRecipeBookRepository;
    @Mock
    private UserAccountRepository mockUserAccountRepository;
    @InjectMocks
    private UserAccountServiceImpl userAccountServiceImpl;

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
        when(mockUserAccountRepository.findByUserName(username))
                .thenReturn(Optional.of(userAccount));

        RecipeBook recipeBook = RecipeBook.builder()
                .userId(userAccount)
                .recipeId(recipeBookId)
                .build();
        when(mockRecipeBookRepository.findById(recipeBookId)).thenReturn(Optional.of(recipeBook));

        doNothing().when(mockRecipeBookRepository).delete(recipeBook);

        userAccountServiceImpl.deleteRecipeBook(recipeBookId, username);

        verify(mockUserAccountRepository, times(1))
                .findByUserName(username);
        verify(mockRecipeBookRepository, times(1)).findById(recipeBookId);
        verify(mockRecipeBookRepository, times(1)).delete(recipeBook);
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

        doThrow(new UserNotFoundException(ExceptionMessages.USER_SESSION_NOT_FOUND))
                .when(mockUserAccountRepository).findByUserName(username);

        RecipeBook recipeBook = RecipeBook.builder()
                .userId(userAccount)
                .recipeId(recipeBookId)
                .build();

        Exception exception = assertThrows(UserNotFoundException.class,
                () -> userAccountServiceImpl.deleteRecipeBook(recipeBookId, username));

        String actualMessage = exception.getMessage();
        assertEquals(actualMessage, "User Session not found !");


        verify(mockUserAccountRepository, times(1))
                .findByUserName(username);
        verify(mockRecipeBookRepository, times(0)).findById(recipeBookId);
        verify(mockRecipeBookRepository, times(0)).delete(recipeBook);
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
        when(mockUserAccountRepository.findByUserName(username))
                .thenReturn(Optional.of(userAccount));

        RecipeBook recipeBook = RecipeBook.builder()
                .userId(userAccount)
                .recipeId(recipeBookId)
                .build();

        doThrow(new NoRecipeBookFoundException(ExceptionMessages.NO_RECIPE_FOUND))
                .when(mockRecipeBookRepository).findById(recipeBookId);

        Exception exception = assertThrows(NoRecipeBookFoundException.class,
                () -> userAccountServiceImpl.deleteRecipeBook(recipeBookId, username));

        String actualMessage = exception.getMessage();
        assertEquals(actualMessage, "There are no recipes saved ");


        verify(mockUserAccountRepository, times(1))
                .findByUserName(username);
        verify(mockRecipeBookRepository, times(1)).findById(recipeBookId);
        verify(mockRecipeBookRepository, times(0)).delete(recipeBook);
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
        when(mockUserAccountRepository.findByUserName(username))
                .thenReturn(Optional.of(userAccount));

        RecipeBook recipeBook = RecipeBook.builder()
                .userId(userAccount2)
                .recipeId(recipeBookId)
                .build();
        when(mockRecipeBookRepository.findById(recipeBookId)).thenReturn(Optional.of(recipeBook));

        doNothing().when(mockRecipeBookRepository).delete(recipeBook);

        Exception exception = assertThrows(RecipeNotBelongToUserException.class,
                () -> userAccountServiceImpl.deleteRecipeBook(recipeBookId, username));

        String actualMessage = exception.getMessage();
        assertEquals(actualMessage, "Recipe not belong to user. Please check and try again.");

        verify(mockUserAccountRepository, times(1))
                .findByUserName(username);
        verify(mockRecipeBookRepository, times(1)).findById(recipeBookId);
        verify(mockRecipeBookRepository, times(0)).delete(recipeBook);
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

        when(mockUserAccountRepository.findByUserName(username)).thenReturn(Optional.of(userAccount));
        doNothing().when(mockUserAccountRepository).deleteById(userAccount.getId());

        userAccountServiceImpl.deleteUserByUsername(username);

        verify(mockUserAccountRepository, times(1))
                .findByUserName(username);
        verify(mockUserAccountRepository, times(1)).deleteById(userAccount.getId());
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

        doThrow(new UserNotFoundException(ExceptionMessages.USER_ACCOUNT_NOT_FOUND))
                .when(mockUserAccountRepository).findByUserName(username);

        Exception exception = assertThrows(UserNotFoundException.class,
                () -> userAccountServiceImpl.deleteUserByUsername(username));

        String actualMessage = exception.getMessage();
        assertEquals(actualMessage, "User not found. Please check and try again.");

        verify(mockUserAccountRepository, times(1))
                .findByUserName(username);
        verify(mockUserAccountRepository, times(0)).deleteById(userAccount.getId());
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

        when(mockUserAccountRepository.existsById(userId)).thenReturn(true);
        doNothing().when(mockUserAccountRepository).deleteById(userAccount.getId());

        userAccountServiceImpl.deleteUserById(userId);

        verify(mockUserAccountRepository, times(1))
                .existsById(userId);
        verify(mockUserAccountRepository, times(1)).deleteById(userAccount.getId());
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

        when(mockUserAccountRepository.existsById(userId)).thenReturn(false);

        Exception exception = assertThrows(UserNotFoundException.class,
                () -> userAccountServiceImpl.deleteUserById(userId));

        String actualMessage = exception.getMessage();
        assertEquals(actualMessage, "User not found. Please check and try again.");

        verify(mockUserAccountRepository, times(1))
                .existsById(userId);
        verify(mockUserAccountRepository, times(0)).deleteById(userAccount.getId());
    }


}
