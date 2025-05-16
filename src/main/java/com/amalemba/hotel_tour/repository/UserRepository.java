package com.amalemba.hotel_tour.repository;

import com.amalemba.hotel_tour.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
