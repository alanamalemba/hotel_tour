package com.amalemba.hotel_tour.mapper;

import com.amalemba.hotel_tour.dto.SignUpRequestBody;
import com.amalemba.hotel_tour.dto.UserDto;
import com.amalemba.hotel_tour.model.User;

public class UserMapper {
    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public static User toEntity(SignUpRequestBody dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }
}
