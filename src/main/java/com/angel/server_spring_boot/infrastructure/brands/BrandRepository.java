package com.angel.server_spring_boot.infrastructure.brands;

import com.angel.server_spring_boot.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByName(String name);

    List<Brand> findByDeletedFalse();

    Optional<Brand> findByIdAndDeletedFalse(Long id);

    Optional<Brand> findByNameAndDeletedFalse(String name);

}