package com.techreturners.weatheripe.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.weatheripe.exception.ExceptionHandlerController;
import com.techreturners.weatheripe.recipe.service.RecipeServiceImpl;
import com.techreturners.weatheripe.user.UserAccountServiceImpl;
import com.techreturners.weatheripe.user.dto.RecipeBookDTO;
import com.techreturners.weatheripe.user.dto.RecipeBookRequestDTO;
import com.techreturners.weatheripe.user.dto.UserRecipeBookDTO;
import com.techreturners.weatheripe.user.dto.UserRecipeBookResponseDTO;
import com.techreturners.weatheripe.weather.service.WeatherServiceImpl;
import org.hamcrest.core.Is;
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
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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


//    @Test
//    public void testUpdateSingleRecipeBook() throws Exception {
//        Principal mockPrincipal = mock(Principal.class);
//        String username = "username";
//        when(mockPrincipal.getName()).thenReturn(username);
//
//        RecipeBookDTO bookDTO1 = RecipeBookDTO.builder().recipeId(1L).rating(1d).isFavourite(true).build();
//        RecipeBookRequestDTO recipeBookRequestDTO = RecipeBookRequestDTO.builder()
//                .recipeBooks(new RecipeBookDTO[]{bookDTO1}).build();
//
//        UserRecipeBookResponseDTO userRecipeBookResponseDTO = new UserRecipeBookResponseDTO();
//
//        userRecipeBookResponseDTO.setUserRecipes(List.of(UserRecipeBookDTO.builder()
//                .recipeBookId(1L)
//                .rating(1d)
//                .build()))
//        ;
//        when(mockUserAccountServiceImpl.updateRecipeBook(recipeBookRequestDTO, username)).thenReturn(userRecipeBookResponseDTO);
//        this.mockMvcController.perform(
//                        MockMvcRequestBuilders
//                                .patch("/api/v1/user/updateRecipeBook")
//                                .principal(mockPrincipal)
//                                .content(mapper.writeValueAsString(recipeBookRequestDTO))
//                                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(jsonPath("$.userRecipes.[0].recipeBookId", Is.is(1)))
//                .andExpect(jsonPath("$.userRecipes.[0].rating", Is.is(1)));
//
//
//    }
//
//
//    @Test
//    public void testUpdateRecipeBookMultipleRecords() throws Exception {
//
//        Principal mockPrincipal = mock(Principal.class);
//        String username = "username";
//        when(mockPrincipal.getName()).thenReturn(username);
//
//        RecipeBookDTO bookDTO1 = RecipeBookDTO.builder().recipeId(1L).rating(1d).isFavourite(true).build();
//        RecipeBookDTO bookDTO2 = RecipeBookDTO.builder().recipeId(2L).rating(2.5d).isFavourite(false).build();
//        RecipeBookRequestDTO recipeBookRequestDTO = RecipeBookRequestDTO.builder()
//                .recipeBooks(new RecipeBookDTO[]{bookDTO1, bookDTO2}).build();
//
//        UserRecipeBookResponseDTO userRecipeBookResponseDTO = new UserRecipeBookResponseDTO();
//
//        userRecipeBookResponseDTO.setUserRecipes(List.of(UserRecipeBookDTO.builder()
//                .recipeBookId(1L)
//                .rating(1d)
//                .build()))
//        ;
//        when(mockUserAccountServiceImpl.updateRecipeBook(recipeBookRequestDTO, username)).thenReturn(userRecipeBookResponseDTO);
//        this.mockMvcController.perform(
//                        MockMvcRequestBuilders
//                                .patch("/api/v1/user/updateRecipeBook")
//                                .principal(mockPrincipal)
//                                .content(mapper.writeValueAsString(recipeBookRequestDTO))
//                                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(jsonPath("$.userRecipes.[0].recipeBookId", Is.is(1)))
//                .andExpect(jsonPath("$.userRecipes.[0].rating", Is.is(1)))
//                .andExpect(jsonPath("$.userRecipes.[1].recipeBookId", Is.is(2)))
//                .andExpect(jsonPath("$.userRecipes.[1].rating", Is.is(2.5)));
//
//    }
//
//
//    @Test
//    public void testUpdateRecipeBookInvalidRecipeIds() throws Exception {
//
//        Principal mockPrincipal = mock(Principal.class);
//        String username = "username";
//        when(mockPrincipal.getName()).thenReturn(username);
//
//        RecipeBookDTO bookDTO1 = RecipeBookDTO.builder().recipeId(1L).rating(1d).isFavourite(true).build();
//        RecipeBookDTO bookDTO2 = RecipeBookDTO.builder().recipeId(2L).rating(2.5d).isFavourite(false).build();
//        RecipeBookRequestDTO recipeBookRequestDTO = RecipeBookRequestDTO.builder()
//                .recipeBooks(new RecipeBookDTO[]{bookDTO1, bookDTO2}).build();
//
//        UserRecipeBookResponseDTO userRecipeBookResponseDTO = new UserRecipeBookResponseDTO();
//
//        userRecipeBookResponseDTO.setUserRecipes(List.of(UserRecipeBookDTO.builder()
//                .recipeBookId(3L)
//                .rating(1d)
//                .build()))
//        ;
//        when(mockUserAccountServiceImpl.updateRecipeBook(recipeBookRequestDTO, username)).thenReturn(userRecipeBookResponseDTO);
//        this.mockMvcController.perform(
//                        MockMvcRequestBuilders
//                                .patch("/api/v1/user/updateRecipeBook")
//                                .principal(mockPrincipal)
//                                .content(mapper.writeValueAsString(recipeBookRequestDTO))
//                                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isNotFound())
//                .andExpect(jsonPath("$.error", Is.is("RECORD_NOT_FOUND")))
//                .andExpect(jsonPath("$.message", Is.is("There are no recipes saved")));
//
//    }

}
