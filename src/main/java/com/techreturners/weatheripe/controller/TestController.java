package com.techreturners.weatheripe.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    //TODO REMOVE
    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String userAccess( Principal principal) {

        System.out.println(principal.getName());
        return principal.getName();
    }

    @GetMapping("/user2")
    public String userAccess2() {
        return "User Content 2.";
    }
}
