package com.angel.server_spring_boot.projections.products;

//  Tipos requeridos
import java.math.BigDecimal;
import java.util.List;

// Nota: Las projecciones no pueden retornar el array de productos
public interface ProductDetailProjection {
    Long getId();
    String getName();
    BigDecimal getPrice();
    String getDescription();
    String getCategoryName();
    String getBrandName();
}
