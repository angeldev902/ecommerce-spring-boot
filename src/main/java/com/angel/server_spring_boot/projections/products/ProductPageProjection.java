package com.angel.server_spring_boot.projections.products;

//  Tipos requeridos
import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDateTime;

public interface ProductPageProjection {
    Long getId();
    String getName();
    String getCode();
    BigDecimal getPrice();
    LocalDateTime getCreatedAt(); // Debe de ser del mismo tipo que en modelo
}
