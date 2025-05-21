package com.amalemba.hotel_tour.controller;

import com.amalemba.hotel_tour.model.Hotel;
import com.amalemba.hotel_tour.security.UserPrincipal;
import com.amalemba.hotel_tour.service.HotelService;
import com.amalemba.hotel_tour.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @GetMapping
    public ResponseEntity<?> getAllHotels(HttpServletRequest httpServletRequest) {

        List<Hotel> hotels = hotelService.getAllHotels();

        return ResponseBuilder.buildSuccess("Hotels fetched successfully", hotels);
    }
}
