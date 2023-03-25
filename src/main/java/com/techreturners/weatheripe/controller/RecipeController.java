package com.techreturners.weatheripe.controller;

import com.techreturners.weatheripe.exception.ErrorResponseDTO;
import com.techreturners.weatheripe.exception.userauthentication.UserSessionNotFoundException;
import com.techreturners.weatheripe.external.dto.ResponseDTO;
import com.techreturners.weatheripe.recipe.service.RecipeService;
import com.techreturners.weatheripe.user.dto.UserRecipeBookResponseDTO;
import com.techreturners.weatheripe.weather.dto.WeatherApiDTO;
import com.techreturners.weatheripe.weather.service.WeatherService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/api/v1/recipe")
public class RecipeController {
    @Autowired
    private WeatherService weatherService;


    @Autowired
    private RecipeService recipeService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response !",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserRecipeBookResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Unable to find food type that match the weather of the specified location. Please check and try again.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "412", description = "Input parameter -location is empty ! ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "417", description = "Unable to find food type that match the weather of the specified location. Please check and try again.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))) ,
            @ApiResponse(responseCode = "511", description = "Invalid API key .Please contact the admin team",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping({"/{location}"})
    public ResponseEntity<ResponseDTO> getWeatherByLocation(@PathVariable String location) {
        WeatherApiDTO weatherApiDTO = (WeatherApiDTO) weatherService.getWeatherByLocation(location);
        ResponseDTO recipeQueryDTO = weatherService.buildExternalRecipeAPIQuery(weatherApiDTO);
        ResponseDTO recipeDTO = recipeService.getRecipeByWeatherCondition(recipeQueryDTO);
        return new ResponseEntity<>(recipeDTO, HttpStatus.OK);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response !",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserRecipeBookResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "User Session not found !",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Unable to find food type that match the weather of the specified location. Please check and try again.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "412", description = "Input parameter -location is empty ! ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "417", description = "Unable to find food type that match the weather of the specified location. Please check and try again.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))) ,
            @ApiResponse(responseCode = "511", description = "Invalid API key .Please contact the admin team",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping({"/user/{location}"})
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO> getWeatherByLocationForUser(@PathVariable String location, Principal principal) {
        ResponseDTO responseDTO = null;
        try {
            responseDTO = (UserRecipeBookResponseDTO) weatherService.getRecipeByLocationForUser(location, principal.getName());

        } catch (UserSessionNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
