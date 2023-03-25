package com.techreturners.weatheripe.controller;

import com.techreturners.weatheripe.external.dto.ResponseDTO;
import com.techreturners.weatheripe.user.dto.UserMessageDTO;
import com.techreturners.weatheripe.user.service.UserAccountService;
import com.techreturners.weatheripe.user.dto.RecipeBookRequestDTO;
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

    @RolesAllowed("ROLE_USER")
    @GetMapping({"/recipeBook"})
    public ResponseEntity<ResponseDTO> getUserRecipeBooks(Principal principal) {

        ResponseDTO responseDTO = userAccountService.getUserRecipeBooks(principal.getName());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PatchMapping({"/updateRecipeBook"})
    public ResponseEntity<ResponseDTO> updateRecipeBook(@Valid @RequestBody RecipeBookRequestDTO recipeBookRequestDTO,Principal principal){

        ResponseDTO responseDTO = userAccountService.updateRecipeBook(recipeBookRequestDTO,principal.getName());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping({"/recipeBook/{recipeBookId}"})
    public ResponseEntity<UserMessageDTO> deleteRecipeBook(@PathVariable Long recipeBookId, Principal principal){

        userAccountService.deleteRecipeBook(recipeBookId,principal.getName());
        return ResponseEntity.ok(new UserMessageDTO("User recipe deleted successfully!"));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping
    public ResponseEntity<UserMessageDTO> unregisterUser(Principal principal) {
        userAccountService.deleteUserByUsername(principal.getName());
        return ResponseEntity.ok(new UserMessageDTO("User unregistered successfully!"));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<UserMessageDTO> unregisterUser(@PathVariable Long userId) {
        userAccountService.deleteUserById(userId);
        return ResponseEntity.ok(new UserMessageDTO("User unregistered successfully!"));
    }

}
