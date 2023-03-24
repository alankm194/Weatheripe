package com.techreturners.weatheripe.controller;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.weatheripe.exception.ExceptionHandlerController;
import com.techreturners.weatheripe.exception.ExceptionMessages;
import com.techreturners.weatheripe.exception.NoRecipeFoundException;
import com.techreturners.weatheripe.exception.UserSessionNotFoundException;
import com.techreturners.weatheripe.user.UserAccountServiceImpl;
import com.techreturners.weatheripe.user.dto.RecipeBookDTO;
import com.techreturners.weatheripe.user.dto.RecipeBookRequestDTO;
import com.techreturners.weatheripe.user.dto.UserRecipeBookDTO;
import com.techreturners.weatheripe.user.dto.UserRecipeBookResponseDTO;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest
public class UserServiceControllerTest {
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
    public void testUpdateSingleRecipeBook() throws Exception {
        Principal mockPrincipal = mock(Principal.class);
        String username = "username";
        when(mockPrincipal.getName()).thenReturn(username);

        RecipeBookDTO bookDTO1 = RecipeBookDTO.builder().recipeId(1L).rating(1d).isFavourite(true).build();
        RecipeBookRequestDTO recipeBookRequestDTO = RecipeBookRequestDTO.builder()
                .recipeBooks(new RecipeBookDTO[]{bookDTO1}).build();

        UserRecipeBookResponseDTO userRecipeBookResponseDTO = new UserRecipeBookResponseDTO();

        userRecipeBookResponseDTO.setUserRecipes(List.of(UserRecipeBookDTO.builder()
                .recipeBookId(1L)
                .rating(1d)
                .build()))
        ;
        when(mockUserAccountServiceImpl.updateRecipeBook(recipeBookRequestDTO, username)).thenReturn(userRecipeBookResponseDTO);
        this.mockMvcController.perform(
                        MockMvcRequestBuilders
                                .patch("/api/v1/user/updateRecipeBook")
                                .principal(mockPrincipal)
                                .content(mapper.writeValueAsString(recipeBookRequestDTO))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.userRecipes.[0].recipeBookId", Is.is(1)))
                .andExpect(jsonPath("$.userRecipes.[0].rating", Is.is(1.0)));


    }


    @Test
    public void testUpdateRecipeBookMultipleRecords() throws Exception {

        Principal mockPrincipal = mock(Principal.class);
        String username = "username";
        when(mockPrincipal.getName()).thenReturn(username);

        RecipeBookDTO bookDTO1 = RecipeBookDTO.builder().recipeId(1L).rating(1d).isFavourite(true).build();
        RecipeBookDTO bookDTO2 = RecipeBookDTO.builder().recipeId(2L).rating(2.5d).isFavourite(false).build();
        RecipeBookRequestDTO recipeBookRequestDTO = RecipeBookRequestDTO.builder()
                .recipeBooks(new RecipeBookDTO[]{bookDTO1, bookDTO2}).build();

        UserRecipeBookResponseDTO userRecipeBookResponseDTO = new UserRecipeBookResponseDTO();

        userRecipeBookResponseDTO.setUserRecipes(List.of(UserRecipeBookDTO.builder()
                .recipeBookId(1L)
                .rating(1d)
                .build(),UserRecipeBookDTO.builder()
                .recipeBookId(2L)
                .rating(2.5d)
                .build()));



        when(mockUserAccountServiceImpl.updateRecipeBook(recipeBookRequestDTO, username)).thenReturn(userRecipeBookResponseDTO);
        this.mockMvcController.perform(
                        MockMvcRequestBuilders
                                .patch("/api/v1/user/updateRecipeBook")
                                .principal(mockPrincipal)
                                .content(mapper.writeValueAsString(recipeBookRequestDTO))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.userRecipes.[0].recipeBookId", Is.is(1)))
                .andExpect(jsonPath("$.userRecipes.[0].rating", Is.is(1.0)))
                .andExpect(jsonPath("$.userRecipes.[1].recipeBookId", Is.is(2)))
                .andExpect(jsonPath("$.userRecipes.[1].rating", Is.is(2.5)));

    }

    @Test
    public void testGetUserRecipeBooks() throws Exception {
        Principal mockPrincipal = mock(Principal.class);
        String username = "username";
        when(mockPrincipal.getName()).thenReturn(username);

        Resource resource = new ClassPathResource("/savedRecipe.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        UserRecipeBookResponseDTO userRecipeBookResponseDTO = objectMapper.readValue(resource.getInputStream(), UserRecipeBookResponseDTO.class);

        when(mockUserAccountServiceImpl.getUserRecipeBooks(username)).thenReturn(userRecipeBookResponseDTO);
        this.mockMvcController.perform(
                           MockMvcRequestBuilders
                                .get("/api/v1/user/recipeBook")
                                .principal(mockPrincipal))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userRecipes.length()").value(20))
                .andExpect(jsonPath("$.userRecipes.[0].recipeBookId", Is.is(1)));


        verify(mockUserAccountServiceImpl, times(1)).getUserRecipeBooks(username);
    }


    @Test
    public void testGetUserRecipeBooksUnregisteredUser() throws Exception {
        Principal mockPrincipal = mock(Principal.class);
        String username = "username";
        when(mockPrincipal.getName()).thenReturn(username);

        doThrow(new UserSessionNotFoundException(ExceptionMessages.USER_SESSION_NOT_FOUND))
                .when(mockUserAccountServiceImpl).getUserRecipeBooks(username);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders
                                .get("/api/v1/user/recipeBook")
                                .principal(mockPrincipal))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("User Session not found !"));

        verify(mockUserAccountServiceImpl, times(1)).getUserRecipeBooks(username);
    }

    @Test
    public void testGetUserRecipeBooksNoRecipe() throws Exception {
        Principal mockPrincipal = mock(Principal.class);
        String username = "username";
        when(mockPrincipal.getName()).thenReturn(username);

        doThrow(new NoRecipeFoundException(ExceptionMessages.NO_RECIPE_FOUND))
                .when(mockUserAccountServiceImpl).getUserRecipeBooks(username);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders
                                .get("/api/v1/user/recipeBook")
                                .principal(mockPrincipal))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("There are no recipes saved "));

        verify(mockUserAccountServiceImpl, times(1)).getUserRecipeBooks(username);
    }
}
