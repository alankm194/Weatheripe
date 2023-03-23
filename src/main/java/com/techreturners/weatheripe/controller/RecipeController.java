package com.techreturners.weatheripe.controller;

import com.techreturners.weatheripe.external.dto.ResponseDTO;
import com.techreturners.weatheripe.recipe.service.RecipeService;
import com.techreturners.weatheripe.user.dto.UserRecipeBookResponseDTO;
import com.techreturners.weatheripe.weather.dto.WeatherApiDTO;
import com.techreturners.weatheripe.weather.service.WeatherService;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/api/v1/recipe")
public class RecipeController {
    @Autowired
    private WeatherService weatherService;


    @Autowired
    private RecipeService recipeService;

    @GetMapping({"/{location}"})
    public ResponseEntity<ResponseDTO> getWeatherByLocation(@PathVariable String location) {
        WeatherApiDTO weatherApiDTO = (WeatherApiDTO) weatherService.getWeatherByLocation(location);
        ResponseDTO recipeQueryDTO = weatherService.buildExternalRecipeAPIQuery(weatherApiDTO);
        ResponseDTO recipeDTO = recipeService.getRecipeByWeatherCondition(recipeQueryDTO);
        return new ResponseEntity<>(recipeDTO, HttpStatus.OK);
    }


//    @GetMapping({"/recipe"})
//    public ResponseEntity<ResponseDTO> getRecipe() {
//        RecipeQueryDTO weatherResponseDTO = new RecipeQueryDTO("https://api.edamam.com/api/recipes/v2?dishType=pancake&dishType=pasta&dishType=maincourse");
//         return new ResponseEntity<>(recipeService.getRecipeByWeatherCondition(weatherResponseDTO), HttpStatus.OK);
//    }


    @GetMapping({"/user/{location}"})
    @RolesAllowed("ROLE_USER")
    public ResponseEntity<ResponseDTO> getWeatherByLocationForUser(@PathVariable String location, Principal principal) {
        ResponseDTO responseDTO = (UserRecipeBookResponseDTO) weatherService.getRecipeByLocationForUser(location, principal.getName());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
