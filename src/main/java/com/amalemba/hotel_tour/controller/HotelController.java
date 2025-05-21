package com.amalemba.hotel_tour.controller;

import com.amalemba.hotel_tour.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @GetMapping
    public ResponseEntity<?> getAllHotels(HttpServletRequest httpServletRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

        Long userId = userDetails.getId();

        return ResponseEntity.ok("User id: " + userId);
    }
}
