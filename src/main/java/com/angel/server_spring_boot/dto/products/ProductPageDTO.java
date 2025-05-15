package com.angel.server_spring_boot.dto.products;

import com.angel.server_spring_boot.domain.Product;
import java.util.List;

import com.angel.server_spring_boot.projections.products.ProductPageProjection;

public class ProductPageDTO {
    private List<ProductPageProjection> data;
    private long total;
    private int page;
    private int limit;

    public ProductPageDTO(List<ProductPageProjection> data, long total, int page, int limit) {
        this.data = data;
        this.total = total;
        this.page = page;
        this.limit = limit;
    }

    // Getter necesario para serialización
    public List<ProductPageProjection> getData() {
        return data;
    }

    public long getTotal() {
        return total;
    }

    public int getPage() {
        return page;
    }

    public int getLimit() {
        return limit;
    }


    @Override
    public String toString() {
        return "Response{" +
                "data:'" + data + '\'' +
                "total:'" + total + '\'' +
                "page:'" + page + '\'' +
                "limit:'" + limit + '\'' +
                '}';
    };
}