package com.angel.server_spring_boot.dto.categories;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public class CategoryDTO {
    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Name must be at least 3 characters long") 
    private String name;

    private Long parentCategory;

    public CategoryDTO() {}

    public CategoryDTO(String name, Long parentCategory) {
        this.name = name;
        this.parentCategory = parentCategory;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentCategory() {
        return parentCategory;
    }

}