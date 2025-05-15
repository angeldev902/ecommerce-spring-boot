package com.angel.server_spring_boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    //RestTemplate becomes a bean that Spring will handle, and you can inject it wherever you need it.
    @Bean
    public RestTemplate restTemplate() { 
        return new RestTemplate();
    }
}
