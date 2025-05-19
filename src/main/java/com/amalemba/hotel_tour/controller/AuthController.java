package com.amalemba.hotel_tour.controller;

import com.amalemba.hotel_tour.dto.SignUpRequestBody;
import com.amalemba.hotel_tour.dto.UserDto;
import com.amalemba.hotel_tour.service.AuthService;
import com.amalemba.hotel_tour.utils.ResponseBuilder;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequestBody signUpRequestBody, HttpServletResponse httpServletResponse) {

        String jwtToken = authService.signUp(signUpRequestBody);

        Cookie cookie = new Cookie("accessToken", jwtToken);

        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60);

        httpServletResponse.addCookie(cookie);

        return ResponseBuilder.buildSuccess("User created successfully!", null);
    }
}
