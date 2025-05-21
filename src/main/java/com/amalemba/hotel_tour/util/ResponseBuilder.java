package com.amalemba.hotel_tour.util;


import com.amalemba.hotel_tour.payload.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {

    public static <T> ResponseEntity<ResponseBody<T>> buildSuccess(String message, T data) {
        return ResponseEntity
                .ok(ResponseBody
                        .<T>builder()
                        .success(true)
                        .message(message)
                        .data(data)
                        .build()
                );
    }

    public static ResponseEntity<ResponseBody<Void>> buildError(HttpStatus status, String message) {
        return ResponseEntity
                .status(status)
                .body(ResponseBody
                        .<Void>builder()
                        .success(false)
                        .message(message)
                        .build()
                );

    }

}
