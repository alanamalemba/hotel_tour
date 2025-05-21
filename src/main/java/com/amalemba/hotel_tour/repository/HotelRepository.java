package com.amalemba.hotel_tour.repository;

import com.amalemba.hotel_tour.model.Hotel;
import com.amalemba.hotel_tour.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByRecommender_Id(Long recommenderId);

    List<Hotel> findByRecommender(User recommender);
}
