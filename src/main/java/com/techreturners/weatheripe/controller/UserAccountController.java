package com.techreturners.weatheripe.controller;

import com.techreturners.weatheripe.external.dto.ResponseDTO;
import com.techreturners.weatheripe.user.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserAccountController {


    @Autowired
    UserAccountService userAccountService ;


    @GetMapping({"/recipeBook"})
    public ResponseEntity<ResponseDTO> getUserRecipeBooks() {
        String token = null ;
        ResponseDTO responseDTO =  userAccountService.getUserRecipeBooks(token);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
