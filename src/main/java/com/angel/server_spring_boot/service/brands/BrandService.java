package com.angel.server_spring_boot.service.brands;

import com.angel.server_spring_boot.domain.Brand;
import com.angel.server_spring_boot.infrastructure.brands.BrandRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.angel.server_spring_boot.dto.brands.NewBrandDTO;

import java.util.List;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) { 
        this.brandRepository = brandRepository;
    }

    public List<Brand> findAll() {
        return brandRepository.findByDeletedFalse();
    }

    public Brand findOneBrand(Long id) {
        Brand brand = brandRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Brand not found" ));
        return brand;
    }

    public Brand save(NewBrandDTO brandDTO) {
        Brand brand = new Brand(brandDTO.getName());
        return brandRepository.save(brand);
    }

    public Brand update(Long id, NewBrandDTO brandDTO) {
        Brand brand = brandRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Brand not found", new Throwable("Details of the error")));
        brand.setName(brandDTO.getName());
        return brandRepository.save(brand);
    }

    public Brand deleteById(Long id) {
        Brand brand = brandRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Brand not found", new Throwable("Details of the error")));
        brand.setDeleted(true);
        return brandRepository.save(brand);
    }

    public Brand findByName(String name) {
        return brandRepository.findByNameAndDeletedFalse(name).orElse(null);
    }
}