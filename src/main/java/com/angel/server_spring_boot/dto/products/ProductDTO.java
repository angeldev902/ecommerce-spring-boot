package com.angel.server_spring_boot.dto.products;

import jakarta.validation.constraints.*; // Es mejor que llamar una por una

import java.time.LocalDate;
import java.util.List;
import java.math.BigDecimal;


public class ProductDTO {
    @NotBlank(message = "Name is required")  // No permite valores nulos ni vacíos
    @Size(min = 3, message = "Name must be at least 3 characters long") 
    private String name;

    @NotBlank(message = "Description is required")  // No permite valores nulos ni vacíos
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Price must have max 10 digits and 2 decimal places")
    private BigDecimal price;

    @NotNull(message = "Shipping Cost is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Shipping Cost must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Shipping Cost must have max 10 digits and 2 decimal places")
    private BigDecimal shippingCost;

    @NotNull(message = "Category ID is required")
    @Positive(message = "Category ID must be a positive number")
    private Long category; // ID de la categoría

    @NotNull(message = "Category ID is required")
    @Positive(message = "Category ID must be a positive number")
    private Long brand; // ID de la categoría

    public ProductDTO() {} // Constructor vacío (obligatorio para Spring y Jackson) Este constructor vacío es necesario para que Spring pueda crear instancias de UserDTO cuando recibe una petición HTTP.

    public ProductDTO(String name, String description, BigDecimal price, BigDecimal shippingCost, Long category, Long brand) { // Constructor con parámetros (opcional, útil para crear objetos manualmente)
        this.name = name;
        this.description = description;
        this.price = price;
        this.shippingCost = shippingCost;
        this.category = category;
        this.brand = brand;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public Long getCategory() {
        return category;
    }

    public Long getBrand() {
        return brand;
    }
    
}