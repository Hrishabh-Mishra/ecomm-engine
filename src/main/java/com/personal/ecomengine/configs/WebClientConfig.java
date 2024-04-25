package com.personal.ecomengine.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    private static final String BASE_URL = "https://fakestoreapi.com";
    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
            .baseUrl(BASE_URL) // Base URL for all requests
            //.defaultHeader("Content-Type", "application/json")
            .build();
    }
}
