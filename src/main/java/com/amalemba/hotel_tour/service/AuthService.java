package com.amalemba.hotel_tour.service;

import com.amalemba.hotel_tour.dto.SignInRequestBody;
import com.amalemba.hotel_tour.dto.SignUpRequestBody;
import com.amalemba.hotel_tour.exception.PasswordsDoNotMatchException;
import com.amalemba.hotel_tour.exception.UserAlreadyExistsException;
import com.amalemba.hotel_tour.exception.UserDoesNotExistException;
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
        Optional<User> optionalUser = userRepository.findByEmailOrPhoneNumber(signUpRequestBody.getEmail(), signUpRequestBody.getPhoneNumber());

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            if (existingUser.getEmail().equals(signUpRequestBody.getEmail())) {
                throw new UserAlreadyExistsException("User with this email already exists");
            } else {
                throw new UserAlreadyExistsException("User with this phone number already exists");
            }
        }

        String hashedPassword = bCryptPasswordEncoder.encode(signUpRequestBody.getPassword());

        User userEntity = UserMapper.toInsertEntity(signUpRequestBody, hashedPassword);

        User newUser = userRepository.save(userEntity);

        // Generate JWT token using JwtService
        return jwtService.generateToken(newUser.getId());
    }

    public String signIn(@Valid SignInRequestBody signInRequestBody) {
        // First check if a user exists
        Optional<User> optionalUser = userRepository.findByEmail(signInRequestBody.getEmail());

        if (optionalUser.isEmpty()) {
            throw new UserDoesNotExistException("User with this email does not exits");
        }

        User existingUser = optionalUser.get();

        boolean passwordsMatch = bCryptPasswordEncoder.matches(signInRequestBody.getPassword(), existingUser.getHashedPassword());

        if (!passwordsMatch) {
            throw new PasswordsDoNotMatchException("Invalid email or password");
        }

        return jwtService.generateToken(existingUser.getId());
    }
}
