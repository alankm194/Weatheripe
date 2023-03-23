package com.techreturners.weatheripe.controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    //TODO REMOVE
    @GetMapping("/user")
    @RolesAllowed("ROLE_USER")
    public String userAccess(Principal principal) {
        System.out.println(principal.getName());
        return "User Content.";
    }

    @GetMapping("/user2")
    public String userAccess2() {
        return "User Content 2.";
    }
}
