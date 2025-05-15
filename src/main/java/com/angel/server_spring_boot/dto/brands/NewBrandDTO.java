package com.angel.server_spring_boot.dto.brands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NewBrandDTO {
    @NotBlank(message = "Name is required") 
    @Size(min = 3, message = "Name must be at least 3 characters long") 
    private String name;

    public NewBrandDTO() {}

    public NewBrandDTO(String name) {
        this.name = name;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "name:'" + name + '\'' +
                '}';
    };
}