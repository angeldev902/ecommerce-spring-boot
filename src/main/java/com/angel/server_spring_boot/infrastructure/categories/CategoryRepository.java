package com.angel.server_spring_boot.infrastructure.categories;

import com.angel.server_spring_boot.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Buscar solo marcas que no están eliminadas
    List<Category> findByDeletedFalse();

    // Buscar por ID solo si no está eliminada
    Optional<Category> findByIdAndDeletedFalse(Long id);

    // Buscar por nombre (asegurando que no está eliminada)
    Optional<Category> findByNameAndDeletedFalse(String name);

    // Buscar por nombre solo si no está eliminada
    boolean existsByNameAndDeletedFalse(String name);
    //Optional<Category> existsByNameAndDeletedFalse(String name);
}