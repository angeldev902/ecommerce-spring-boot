package com.angel.server_spring_boot.dto.auth;

public class LoginResponseDTO {
    private String accessToken;

    public LoginResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    // Getter necesario para serialización
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String toString() {
        return "Response{" +
                "accessToken:'" + accessToken + '\'' +
                '}';
    };
}