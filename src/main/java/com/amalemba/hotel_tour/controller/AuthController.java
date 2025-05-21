package com.amalemba.hotel_tour.controller;

import com.amalemba.hotel_tour.dto.SignInRequestBody;
import com.amalemba.hotel_tour.dto.SignUpRequestBody;
import com.amalemba.hotel_tour.service.AuthService;
import com.amalemba.hotel_tour.utils.ResponseBuilder;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Optional;

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

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(
            @Valid @RequestBody SignInRequestBody signInRequestBody,
            HttpServletResponse httpServletResponse
    ) {

        String jwtToken = authService.signIn(signInRequestBody);

        Cookie cookie = new Cookie("accessToken", jwtToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60);

        httpServletResponse.addCookie(cookie);

        return ResponseBuilder.buildSuccess("Signed in successfully!", null);
    }

    @PostMapping("/sign-out")
    public ResponseEntity<?> signOut(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return ResponseBuilder.buildError(HttpStatus.BAD_REQUEST, "You are not signed in");
        }

        Optional<Cookie> accessTokenCookie = Arrays.stream(cookies)
                                                   .filter(cookie -> "accessToken".equals(cookie.getName()))
                                                   .findFirst();

        if (accessTokenCookie.isEmpty()) {
            return ResponseBuilder.buildError(HttpStatus.BAD_REQUEST, "You are not signed in");
        }

        Cookie cookie = new Cookie("accessToken", "");
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0); // Deletes cookie

        response.addCookie(cookie);

        return ResponseBuilder.buildSuccess("Signed out successfully", null);
    }
}
