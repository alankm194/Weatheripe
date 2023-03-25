package com.techreturners.weatheripe.controller;

import com.techreturners.weatheripe.dish.DishService;
import com.techreturners.weatheripe.model.recipe.DishType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/dishtypes")
public class MasterDataController {

    @Autowired
    DishService dishService;

    @GetMapping
    public ResponseEntity<List<DishType>> getAllDishTypesFromMaster() {
        List<DishType> returnedDishes = dishService.getAllDishTypes();
        return new ResponseEntity<>(returnedDishes, HttpStatus.OK);
    }
}
