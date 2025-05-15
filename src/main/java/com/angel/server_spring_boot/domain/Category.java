package com.angel.server_spring_boot.domain;
import jakarta.persistence.*;

import java.util.List;
import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private List<Category> subCategories;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Category() {}

    public Category(String name, String code, Category parentCategory) {
        this.name = name;
        this.code = code;
        this.parentCategory = parentCategory;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", code:'" + code + '\'' +
                ", parentCategory:'" + parentCategory + '\'' +
                '}';
    };

    // Getters y Setters
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

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) { 
        this.parentCategory = parentCategory;
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