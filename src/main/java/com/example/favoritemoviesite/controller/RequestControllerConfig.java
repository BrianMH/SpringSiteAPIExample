package com.example.favoritemoviesite.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RequestControllerConfig {
    @Bean
    public WebClient movieReqClient() {
        return WebClient.create("http://omdbapi.com/");
    }
}
