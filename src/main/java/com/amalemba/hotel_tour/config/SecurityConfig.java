package com.amalemba.hotel_tour.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/","/sing-in","/sing-up").permitAll() // Allow public access
                .anyRequest().authenticated() // Every other route needs auth
        ).csrf(csrf -> csrf.disable()); // Modern way to disable CSRF

        return http.build();
    }
}
