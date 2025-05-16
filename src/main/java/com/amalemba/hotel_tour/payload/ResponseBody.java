package com.amalemba.hotel_tour.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseBody<T> {
    private boolean success;
    private String message;
    private T data;
    private Metadata metadata;



    public static class Metadata {
        private Integer page;
        private Integer size;
        private String query;
        private Long totalRecords;
        private  Integer totalPages;
    }


}
