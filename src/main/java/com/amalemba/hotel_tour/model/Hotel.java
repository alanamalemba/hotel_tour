package com.amalemba.hotel_tour.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hotels")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "image_url")
    private String imageUrl;


    @Column(nullable = false, name = "is_visited", columnDefinition = "BOOLEAN DEFAULT FALSE")  // - columnDefinition = "BOOLEAN DEFAULT FALSE": sets default value to FALSE at the DB level
    @Builder.Default // Ensures the default value is used when using Lombok's @Builder (otherwise it would be ignored)
    private boolean isVisited = false; // Java-level default value; ensures isVisited is false unless explicitly set

    @ManyToOne(optional = false) // A hotel must have a recommender
    @JoinColumn(name = "recommender_id", referencedColumnName = "id") // Maps to users.id
    private User recommender;


}
