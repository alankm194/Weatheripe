package com.techreturners.weatheripe.controller;

import com.techreturners.weatheripe.dish.DishService;
import com.techreturners.weatheripe.model.recipe.DishType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/dishtypes")
public class MasterDataController {

    @Autowired
    DishService dishService;

    @Operation(summary = "Get all dish types from dish table")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dish types retrieved from dish table",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DishType.class)) })
    })


    @GetMapping
    public ResponseEntity<List<DishType>> getAllDishTypesFromMaster() {
        List<DishType> returnedDishes = dishService.getAllDishTypes();
        return new ResponseEntity<>(returnedDishes, HttpStatus.OK);
    }
}
