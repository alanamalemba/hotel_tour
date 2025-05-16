package com.amalemba.hotel_tour.controller;

import com.amalemba.hotel_tour.utils.ResponseBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp() {
        return ResponseBuilder.buildSuccess("sign up hit successfully!","Data goes here");
    }
}
