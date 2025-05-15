package com.angel.server_spring_boot.infrastructure.products;

import com.angel.server_spring_boot.domain.Product;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

//Projections
import com.angel.server_spring_boot.projections.products.ProductListProjection;
import com.angel.server_spring_boot.projections.products.ProductDetailProjection;
import com.angel.server_spring_boot.projections.products.ProductPageProjection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Buscar solo marcas que no están eliminadas
    List<ProductListProjection> findByDeletedFalse();

    // Buscar por ID solo si no está eliminada
    Optional<Product> findByIdAndDeletedFalse(Long id);

    // Buscar por nombre (asegurando que no está eliminada)
    Optional<Product> findByNameAndDeletedFalse(String name);

    // Buscar por nombre solo si no está eliminada
    boolean existsByNameAndDeletedFalse(String name);
    //Optional<Category> existsByNameAndDeletedFalse(String name);

    // Get product detail
    @Query("""
        SELECT p.id AS id, p.name AS name, p.price AS price, p.description AS description,
            c.name AS categoryName, b.name AS brandName
        FROM Product p
        LEFT JOIN p.category c
        LEFT JOIN p.brand b
        WHERE p.id = :productId 
        AND p.deleted = false
    """)
    ProductDetailProjection findProductDetailsById(@Param("productId") Long productId);

    @Query("""
        SELECT p.pictures FROM Product p WHERE p.id = :productId
    """)
    List<String> findPicturesByProductId(@Param("productId") Long productId);

    //Get product page

    Page<Product> findAll(Pageable pageable);

    Page<ProductPageProjection> findAllBy(Pageable pageable);

    Page<ProductPageProjection> findByNameContainingIgnoreCase(String name, Pageable pageable);

}