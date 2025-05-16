package com.amalemba.hotel_tour.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Marks this class as a JPA entity that maps to a database table
@Table(name = "users") // Explicitly sets the table name to "users" (snake_case and pluralized for consistency and )
@Data // Lombok: generates getters, setters, equals(), hashCode(), toString(), etc.
@NoArgsConstructor // Lombok: generates a no-args constructor (required by JPA)
@AllArgsConstructor // Lombok: generates a constructor with all fields
@Builder
public class User {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Uses MySQL's AUTO_INCREMENT feature
    private Long id;

    @Column(nullable = false) // Cannot be null in the database
    private String name;

    @Column(nullable = false, unique = true) // Must be unique and not null
    private String email;

    @Column(unique = true) // Must be unique, but can be null
    private String phoneNumber;

    @Column(nullable = false, name = "hashed_password") // Password is required
    private String hashedPassword;// todo[]: implement hashing, find out how to use bcrypt for hashing
}
