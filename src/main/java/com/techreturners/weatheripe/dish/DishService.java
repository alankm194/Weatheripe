package com.techreturners.weatheripe.dish;

import com.techreturners.weatheripe.model.DishType;
import com.techreturners.weatheripe.external.dto.ResponseDTO;
import com.techreturners.weatheripe.model.Weather;

import java.util.List;

public interface DishService {
    List<DishType> getAllDishTypes();
}
