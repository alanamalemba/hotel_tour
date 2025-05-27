package com.amalemba.hotel_tour.dto;

import com.amalemba.hotel_tour.model.Hotel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class HotelDto {
    private Long id;
    private String name;
    private String imageUrl;
    private Long recommenderId;
    private boolean visited;

    public HotelDto(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.imageUrl = hotel.getImageUrl();
        this.visited = hotel.isVisited();
        this.recommenderId = (hotel.getRecommender().getId());
    }
}
