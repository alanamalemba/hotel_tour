package com.amalemba.hotel_tour.service;

import com.amalemba.hotel_tour.model.Hotel;
import com.amalemba.hotel_tour.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {

        return hotelRepository.findAll();
    }
}
