package com.amalemba.hotel_tour.controller;

import com.amalemba.hotel_tour.dto.HotelDto;
import com.amalemba.hotel_tour.model.Hotel;
import com.amalemba.hotel_tour.payload.ResponseBody;
import com.amalemba.hotel_tour.service.HotelService;
import com.amalemba.hotel_tour.util.ResponseBuilder;
import com.amalemba.hotel_tour.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @GetMapping
    public ResponseEntity<ResponseBody<List<HotelDto>>> getAllHotels() {

        List<HotelDto> hotels = hotelService.getAllHotels();

        return ResponseBuilder.buildSuccess("Hotels fetched successfully", hotels);
    }

//    @GetMapping("/{hotelId}")
//    public ResponseEntity<ResponseBody<HotelDto>> getHotelById(@RequestParam Long hotelId) {
//// todo
//
//    }

    @PutMapping("/{hotelId}/visited")
    public ResponseEntity<ResponseBody<HotelDto>> getHotelById(@RequestParam Long hotelId) {

        Long markingUserId = SecurityUtils.getCurrentUserId();

     HotelDto markedHotel  =   hotelService.markAsVisited(hotelId,markingUserId);

     return  ResponseBuilder.buildSuccess("Hotel marked as visited successfully!", markedHotel);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseBody<HotelDto>> addHotel(
            @RequestPart(name = "hotelName", required = true) String hotelName,
            @RequestPart(name = "imageFile", required = true) MultipartFile imageFile
    ) {

        // get user id
        Long recommenderId = SecurityUtils.getCurrentUserId();

        HotelDto createdHotel = hotelService.addHotel(hotelName, imageFile, recommenderId);

        return ResponseBuilder.buildSuccess("Hotel recommendation added successfully!", createdHotel);
    }
}
