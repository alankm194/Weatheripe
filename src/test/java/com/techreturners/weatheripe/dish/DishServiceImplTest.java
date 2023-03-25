package com.techreturners.weatheripe.dish;

import com.techreturners.weatheripe.configuration.SecretConfiguration;
import com.techreturners.weatheripe.model.recipe.DishType;
import com.techreturners.weatheripe.repository.DishTypeRepository;
import com.techreturners.weatheripe.weather.service.WeatherServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
class DishServiceImplTest {

    @Mock
    private DishTypeRepository dishTypeRepository;
    @InjectMocks
    private DishServiceImpl dishService;

    @Test
    void getAllDishTypes() {

        List<DishType> returnedDishes = new ArrayList<DishType>();
        DishType dt1 = new DishType(1L, "pasta");
        returnedDishes.add(dt1);

        when(dishTypeRepository.findAll()).thenReturn(returnedDishes);

        List<DishType> allDishes = dishService.getAllDishTypes();

        verify(dishTypeRepository, times(1)).findAll();

        assertEquals(returnedDishes,allDishes);
    }
}