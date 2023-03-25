package com.techreturners.weatheripe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.weatheripe.exception.*;
import com.techreturners.weatheripe.user.UserAccountServiceImpl;
import com.techreturners.weatheripe.weather.service.WeatherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;

import static org.mockito.Mockito.*;


@AutoConfigureMockMvc
@SpringBootTest
public class UserAccountControllerTest {
    @Mock
    private WeatherServiceImpl mockWeatherServiceImpl;

    @Mock
    private UserAccountServiceImpl mockUserAccountServiceImpl;

    @InjectMocks
    private UserAccountController userAccountController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        mockMvcController = MockMvcBuilders.standaloneSetup(userAccountController)
                .setControllerAdvice(new ExceptionHandlerController())
                .build();
        mapper = new ObjectMapper();
    }

    @Test
    public void testDeleteRecipeBookByIdReturnsSuccess() throws Exception {
        Long recipeBookId = 1L;
        String token = "";

        Principal mockPrincipal = mock(Principal.class);
        String username = "username";
        when(mockPrincipal.getName()).thenReturn(username);

        doNothing().when(mockUserAccountServiceImpl).deleteRecipeBook(recipeBookId,username);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders
                                .delete("/api/v1/user/recipeBook/" + recipeBookId)
                                .principal(mockPrincipal)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("User recipe deleted successfully!"));

        verify(mockUserAccountServiceImpl, times(1)).deleteRecipeBook(recipeBookId,username);
    }

    @Test
    public void testDeleteRecipeBookByIdReturnsFailForNoUserSesssion() throws Exception {
        Long recipeBookId = 1L;
        String token = "";

        Principal mockPrincipal = mock(Principal.class);
        String username = "username";
        when(mockPrincipal.getName()).thenReturn(username);

        doThrow(new UserSessionNotFoundException(ExceptionMessages.USER_SESSION_NOT_FOUND))
                .when(mockUserAccountServiceImpl).deleteRecipeBook(recipeBookId, username);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders
                                .delete("/api/v1/user/recipeBook/" + recipeBookId)
                                .principal(mockPrincipal)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(ExceptionMessages.USER_SESSION_NOT_FOUND));

        verify(mockUserAccountServiceImpl, times(1)).deleteRecipeBook(recipeBookId,username);
    }

    @Test
    public void testDeleteRecipeBookByIdReturnsFailForNoRecipeBook() throws Exception {
        Long recipeBookId = 1L;
        String token = "";

        Principal mockPrincipal = mock(Principal.class);
        String username = "username";
        when(mockPrincipal.getName()).thenReturn(username);

        doThrow(new NoRecipeBookFoundException(ExceptionMessages.NO_RECIPE_FOUND))
                .when(mockUserAccountServiceImpl).deleteRecipeBook(recipeBookId, username);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders
                                .delete("/api/v1/user/recipeBook/" + recipeBookId)
                                .principal(mockPrincipal)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(ExceptionMessages.NO_RECIPE_FOUND));

        verify(mockUserAccountServiceImpl, times(1)).deleteRecipeBook(recipeBookId,username);
    }

    @Test
    public void testDeleteRecipeBookByIdReturnsFailForRecipeBookNotBelongToUser() throws Exception {
        Long recipeBookId = 1L;
        String token = "";

        Principal mockPrincipal = mock(Principal.class);
        String username = "username";
        when(mockPrincipal.getName()).thenReturn(username);

        doThrow(new RecipeNotBelongToUserException(ExceptionMessages.RECIPE_NOT_BELONG_TO_USER))
                .when(mockUserAccountServiceImpl).deleteRecipeBook(recipeBookId, username);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders
                                .delete("/api/v1/user/recipeBook/" + recipeBookId)
                                .principal(mockPrincipal)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(ExceptionMessages.RECIPE_NOT_BELONG_TO_USER));

        verify(mockUserAccountServiceImpl, times(1)).deleteRecipeBook(recipeBookId,username);
    }

    @Test
    public void testUnregisterUserReturnsSuccess() throws Exception {
        Long recipeBookId = 1L;
        String token = "";

        Principal mockPrincipal = mock(Principal.class);
        String username = "username";
        when(mockPrincipal.getName()).thenReturn(username);

        doNothing().when(mockUserAccountServiceImpl).deleteUserByUsername(username);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders
                                .delete("/api/v1/user")
                                .principal(mockPrincipal)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("User unregistered successfully!"));

        verify(mockUserAccountServiceImpl, times(1)).deleteUserByUsername(username);
    }

    @Test
    public void testUnregisterUserReturnsFailOnUserAccountNotFound() throws Exception {
        Long recipeBookId = 1L;
        String token = "";

        Principal mockPrincipal = mock(Principal.class);
        String username = "username";
        when(mockPrincipal.getName()).thenReturn(username);

        doThrow(new UserSessionNotFoundException(ExceptionMessages.USER_SESSION_NOT_FOUND))
                .when(mockUserAccountServiceImpl).deleteUserByUsername(username);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders
                                .delete("/api/v1/user")
                                .principal(mockPrincipal)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(ExceptionMessages.USER_SESSION_NOT_FOUND));

        verify(mockUserAccountServiceImpl, times(1)).deleteUserByUsername(username);
    }

    @Test
    public void testUnregisterUserByUserIdReturnsSuccess() throws Exception {
        Long recipeBookId = 1L;
        String token = "";
        Long userId = 1L;

        doNothing().when(mockUserAccountServiceImpl).deleteUserById(userId);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders
                                .delete("/api/v1/user/"+userId)
//                                .principal(mockPrincipal)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("User unregistered successfully!"));

        verify(mockUserAccountServiceImpl, times(1)).deleteUserById(userId);
    }

    @Test
    public void testUnregisterUserByUserIdReturnsFailOnUserAccountNotFound() throws Exception {
        Long recipeBookId = 1L;
        String token = "";
        Long userId = 1L;

        doThrow(new UserSessionNotFoundException(ExceptionMessages.USER_SESSION_NOT_FOUND))
                .when(mockUserAccountServiceImpl).deleteUserById(userId);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders
                                .delete("/api/v1/user/"+userId)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(ExceptionMessages.USER_SESSION_NOT_FOUND));

        verify(mockUserAccountServiceImpl, times(1)).deleteUserById(userId);
    }
}
