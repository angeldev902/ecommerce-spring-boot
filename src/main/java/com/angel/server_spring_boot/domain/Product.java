package com.angel.server_spring_boot.domain;
import jakarta.persistence.*;

import java.util.List;
import java.time.LocalDateTime;
import java.math.BigDecimal;

import com.angel.server_spring_boot.domain.Category;
import com.angel.server_spring_boot.domain.Brand;

@Entity
@Table(name = "products")
public class Product {
    // Columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    @JoinColumn(name = "shipping_cost")
    private BigDecimal shippingCost;

    @Column(columnDefinition = "TEXT")   
    private String description;

    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url")
    private List<String> pictures;

    //-----------Relations
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private boolean deleted = false; // Soft delete

    //Constructs
    public Product() {}

    public Product(
            String name, 
            String code, 
            BigDecimal price, 
            BigDecimal shippingCost, 
            String description, 
            Category category,
            Brand brand,
            List<String> pictures
        ) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.shippingCost = shippingCost;
        this.description = description;
        this.category = category;
        this.brand = brand;
        this.pictures = pictures;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", code:'" + code + '\'' +
                ", price:'" + price + '\'' +
                ", price:'" + price + '\'' +
                ", shippingCost:'" + shippingCost + '\'' +
                ", category:'" + category + '\'' +
                ", brand:'" + brand + '\'' +
                '}';
    };

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { 
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) { 
        this.code = code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) { 
        this.price = price;
    }

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(BigDecimal shippingCost) { 
        this.shippingCost = shippingCost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) { 
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) { 
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) { 
        this.brand = brand;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) { 
        this.pictures = pictures;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}