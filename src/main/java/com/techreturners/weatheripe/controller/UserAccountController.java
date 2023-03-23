package com.techreturners.weatheripe.controller;

import com.techreturners.weatheripe.external.dto.ResponseDTO;
import com.techreturners.weatheripe.response.MessageResponse;
import com.techreturners.weatheripe.security.service.AuthService;
import com.techreturners.weatheripe.user.UserAccountService;
import com.techreturners.weatheripe.user.dto.RecipeBookRequestDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserAccountController {
    @Autowired
    UserAccountService userAccountService;

    @Autowired
    private AuthService authService;


    @RolesAllowed("ROLE_USER")
    @GetMapping({"/recipeBook"})
    public ResponseEntity<ResponseDTO> getUserRecipeBooks(@AuthenticationPrincipal Jwt jwt) {

        ResponseDTO responseDTO = userAccountService.getUserRecipeBooks(jwt.getClaim("sub"));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RolesAllowed("ROLE_USER")
    @PatchMapping({"/updateRecipeBook"})
    public ResponseEntity<ResponseDTO> updateRecipeBook(@Valid @RequestBody RecipeBookRequestDTO recipeBookRequestDTO, @AuthenticationPrincipal Jwt jwt){

        ResponseDTO responseDTO = userAccountService.updateRecipeBook(recipeBookRequestDTO,jwt.getClaim("sub"));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RolesAllowed("ROLE_USER")
    @DeleteMapping("/unregister")
    public ResponseEntity<MessageResponse> unregisterUser(@RequestHeader("Authorization") String jwt) {
        String username = authService.extractUsernameFromToken(jwt);
        userAccountService.deleteUserByUsername(username);
        return ResponseEntity.ok(new MessageResponse("User unregistered successfully!"));
    }

    @RolesAllowed("ROLE_USER")
    @DeleteMapping("/unregister/{userId}")
    public ResponseEntity<MessageResponse> unregisterUser(@PathVariable Long userId) {
        userAccountService.deleteUserById(userId);
        return ResponseEntity.ok(new MessageResponse("User unregistered successfully!"));
    }

}
