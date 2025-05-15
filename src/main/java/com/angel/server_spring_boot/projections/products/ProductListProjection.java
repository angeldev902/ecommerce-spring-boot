package com.angel.server_spring_boot.projections.products;

//  Tipos requeridos
import java.math.BigDecimal;
import java.util.List;

public interface ProductListProjection {
    Long getId();
    String getName();
    BigDecimal getPrice();
    List<String> getPictures();
}
