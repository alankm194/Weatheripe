package com.techreturners.weatheripe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.weatheripe.dish.DishServiceImpl;
import com.techreturners.weatheripe.exception.ExceptionHandlerController;
import com.techreturners.weatheripe.model.recipe.DishType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
class MasterDataControllerTest {

    @Mock
    private DishServiceImpl mockDishService;

    @InjectMocks
    private MasterDataController mockMasterDataController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        mockMvcController = MockMvcBuilders.standaloneSetup(mockMasterDataController)
                .setControllerAdvice(new ExceptionHandlerController()).build();
        mapper = new ObjectMapper();

    }

    @Test
    void getAllDishTypesFromMaster() throws Exception{

        List<DishType> returnedDishes = new ArrayList<DishType>();
        DishType dt1 = new DishType(1L, "pasta");
        returnedDishes.add(dt1);

        when(mockDishService.getAllDishTypes()).thenReturn(returnedDishes);

        MvcResult loginResult = this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/dishtypes"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        verify(mockDishService, times(1)).getAllDishTypes();
    }
}