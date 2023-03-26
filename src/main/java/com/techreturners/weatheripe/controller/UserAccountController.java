package com.techreturners.weatheripe.controller;

import com.techreturners.weatheripe.exception.ErrorResponseDTO;
import com.techreturners.weatheripe.external.dto.ResponseDTO;
import com.techreturners.weatheripe.user.dto.UserMessageDTO;
import com.techreturners.weatheripe.user.service.UserAccountService;
import com.techreturners.weatheripe.user.dto.RecipeBookRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/user" )
public class UserAccountController {
    @Autowired
    UserAccountService userAccountService;

    @Operation(summary = "User retrieve his/her recipe books after login", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieve recipe books successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "User Session not found !",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "No recipe found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @RolesAllowed("ROLE_USER")
    @GetMapping({"/recipeBook"})
    public ResponseEntity<ResponseDTO> getUserRecipeBooks(Principal principal) {

        ResponseDTO responseDTO = userAccountService.getUserRecipeBooks(principal.getName());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "User update recipe books' rating after login", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User update recipe book successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "No recipe Ids in request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid recipe Ids in request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PreAuthorize("hasRole('ROLE_USER')")
    @PatchMapping({"/recipeBook"})
    public ResponseEntity<ResponseDTO> updateRecipeBook(@Valid @RequestBody RecipeBookRequestDTO recipeBookRequestDTO,Principal principal){

        ResponseDTO responseDTO = userAccountService.updateRecipeBook(recipeBookRequestDTO,principal.getName());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "User delete recipe book by id after login", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User delete recipe books successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserMessageDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Recipe not belong to user",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "User Session not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "No recipe found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping({"/recipeBook/{recipeBookId}"})
    public ResponseEntity<UserMessageDTO> deleteRecipeBook(@PathVariable Long recipeBookId, Principal principal){

        userAccountService.deleteRecipeBook(recipeBookId,principal.getName());
        return ResponseEntity.ok(new UserMessageDTO("User recipe deleted successfully!"));
    }


    @Operation(summary = "User unregister him/herself after login", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User unregister successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserMessageDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "User Session not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping
    public ResponseEntity<UserMessageDTO> unregisterUser(Principal principal) {
        userAccountService.deleteUserByUsername(principal.getName());
        return ResponseEntity.ok(new UserMessageDTO("User unregistered successfully!"));
    }

    @Operation(summary = "Unregister user (without login)", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User unregister successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserMessageDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<UserMessageDTO> unregisterUser(@PathVariable Long userId) {
        userAccountService.deleteUserById(userId);
        return ResponseEntity.ok(new UserMessageDTO("User unregistered successfully!"));
    }

}
