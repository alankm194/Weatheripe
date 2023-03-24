package com.techreturners.weatheripe.controller;

import com.techreturners.weatheripe.external.dto.ResponseDTO;
import com.techreturners.weatheripe.response.MessageResponse;
import com.techreturners.weatheripe.user.UserAccountService;
import com.techreturners.weatheripe.user.dto.RecipeBookRequestDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/user", consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserAccountController {
    @Autowired
    UserAccountService userAccountService;

    @RolesAllowed("ROLE_USER")
    @GetMapping({"/recipeBook"})
    public ResponseEntity<ResponseDTO> getUserRecipeBooks(@AuthenticationPrincipal Jwt jwt) {

        ResponseDTO responseDTO = userAccountService.getUserRecipeBooks(jwt.getClaim("sub"));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PatchMapping({"/updateRecipeBook"})
    public ResponseEntity<ResponseDTO> updateRecipeBook(@Valid @RequestBody RecipeBookRequestDTO recipeBookRequestDTO,Principal principal){

        ResponseDTO responseDTO = userAccountService.updateRecipeBook(recipeBookRequestDTO,principal.getName());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping({"/deleteRecipeBook/{recipeBookId}"})
    public ResponseEntity<MessageResponse> deleteRecipeBook(@PathVariable Long recipeBookId, Principal principal){

        userAccountService.deleteRecipeBook(recipeBookId,principal.getName());
        return ResponseEntity.ok(new MessageResponse("User recipe deleted successfully!"));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/unregister")
    public ResponseEntity<MessageResponse> unregisterUser(Principal principal) {
        userAccountService.deleteUserByUsername(principal.getName());
        return ResponseEntity.ok(new MessageResponse("User unregistered successfully!"));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/unregister/{userId}")
    public ResponseEntity<MessageResponse> unregisterUser(@PathVariable Long userId) {
        userAccountService.deleteUserById(userId);
        return ResponseEntity.ok(new MessageResponse("User unregistered successfully!"));
    }

}
