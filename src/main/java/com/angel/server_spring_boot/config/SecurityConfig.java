package com.angel.server_spring_boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;
import com.angel.server_spring_boot.security.JwtFilter;


@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;  // JwtFilter

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors() // Spring Security use CORS config
            .and()
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/hello").permitAll() // Allow acces to testing toutes
                .requestMatchers("/auth/**").permitAll() // Allow access to user's routes
                .requestMatchers("/images/**").permitAll() // Allow access to images
                .requestMatchers(HttpMethod.GET, "/products", "/products/page", "/products/{id}").permitAll() // Solo permite GET
                .requestMatchers(HttpMethod.POST, "/products").authenticated() // Requiere auth para POST
                .requestMatchers("/static/**", "/public/**", "/resources/**").permitAll() // static routes
                .anyRequest().authenticated() // Protect routes with auth
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .formLogin().disable(); // Desactiva el formulario de login predeterminado
        return http.build();
    }
}