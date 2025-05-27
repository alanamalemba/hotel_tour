package com.amalemba.hotel_tour.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                              .title("Hotel Tour API Documentation")
                              .version("1.0")
                              .description("Api documentation for" + " Home tour " + "API backend " + "server")
                );
    }
}
