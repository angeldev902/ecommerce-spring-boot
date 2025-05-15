package com.angel.server_spring_boot.dto.products;

import com.angel.server_spring_boot.projections.products.ProductDetailProjection;
import java.util.List;
import java.math.BigDecimal;

public record ProductDetailDTO(
    Long id,
    String name,
    BigDecimal price,
    List<String> pictures,
    String description,
    String categoryName,
    String brandName
) {
    public ProductDetailDTO(ProductDetailProjection product, List<String> pictures) {
        this(product.getId(), product.getName(), product.getPrice(), pictures,
             product.getDescription(), product.getCategoryName(), product.getBrandName());
    }
}
