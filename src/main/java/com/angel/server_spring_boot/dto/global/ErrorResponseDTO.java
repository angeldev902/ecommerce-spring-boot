package com.angel.server_spring_boot.dto.global;

public class ErrorResponseDTO {
    private String error;

    public ErrorResponseDTO(String error) {
        this.error = error;
    }

    // Getter necesario para serialización
    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "Response{" +
                "error:'" + error + '\'' +
                '}';
    };
}