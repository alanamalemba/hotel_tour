package com.amalemba.hotel_tour.repository;

import com.amalemba.hotel_tour.model.Hotel;
import com.amalemba.hotel_tour.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByRecommender_Id(Long recommenderId);

    List<Hotel> findByRecommender(User recommender);
}
