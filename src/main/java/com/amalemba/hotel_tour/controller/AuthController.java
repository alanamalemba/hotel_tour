package com.amalemba.hotel_tour.controller;

import com.amalemba.hotel_tour.dto.SignUpRequestBody;
import com.amalemba.hotel_tour.dto.UserDto;
import com.amalemba.hotel_tour.model.User;
import com.amalemba.hotel_tour.service.AuthService;
import com.amalemba.hotel_tour.utils.ResponseBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequestBody signUpRequestBody) {

        UserDto newUser = authService.singUp(signUpRequestBody);

        return ResponseBuilder.buildSuccess("User created successfully!", newUser);
    }
}
