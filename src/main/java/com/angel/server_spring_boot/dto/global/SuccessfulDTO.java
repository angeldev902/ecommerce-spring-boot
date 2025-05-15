package com.angel.server_spring_boot.dto.global;

public class SuccessfulDTO {
    private String message;

    public SuccessfulDTO(String message) {
        this.message = message;
    }

    // Getter necesario para serialización
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "message:'" + message + '\'' +
                '}';
    };
}