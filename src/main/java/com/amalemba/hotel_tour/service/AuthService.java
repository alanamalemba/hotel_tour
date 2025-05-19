package com.amalemba.hotel_tour.service;

import com.amalemba.hotel_tour.dto.SignUpRequestBody;
import com.amalemba.hotel_tour.exception.UserAlreadyExistsException;
import com.amalemba.hotel_tour.mapper.UserMapper;
import com.amalemba.hotel_tour.model.User;
import com.amalemba.hotel_tour.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtService jwtService;

    public String signUp(@Valid SignUpRequestBody signUpRequestBody) {
        // First check if a user with similar identifications exists
        Optional<User> optionalUser = userRepository.findByEmail(signUpRequestBody.getEmail());
        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException("User with this email already exists");
        }

        String hashedPassword = bCryptPasswordEncoder.encode(signUpRequestBody.getPassword());

        User userPayload = UserMapper.toEntity(signUpRequestBody);

        userPayload.setHashedPassword(hashedPassword);

        User newUser = userRepository.save(userPayload);

        // Generate JWT token using JwtService
        return jwtService.generateToken(newUser.getId());
    }
}
