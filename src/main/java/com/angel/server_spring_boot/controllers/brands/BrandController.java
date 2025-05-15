package com.angel.server_spring_boot.controllers.brands;

import com.angel.server_spring_boot.domain.Brand;
import com.angel.server_spring_boot.service.brands.BrandService;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.angel.server_spring_boot.dto.brands.NewBrandDTO;
import com.angel.server_spring_boot.dto.global.SuccessfulDTO;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public List<Brand> getAllBrands() {
        return brandService.findAll();
    }
    
    @PostMapping
    public ResponseEntity<?> createBrand(@Valid @RequestBody NewBrandDTO newBrandDTO,  BindingResult result) {
        if (result.hasErrors()) return ResponseEntity.badRequest().body(result.getAllErrors());
        Brand brand = brandService.save(newBrandDTO);
        return ResponseEntity.ok(new SuccessfulDTO("Created brand"));
    }

    @GetMapping("/{id}")
    public Brand getBrand(@PathVariable Long id) {
        return brandService.findOneBrand(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBrand(@PathVariable Long id, @Valid @RequestBody NewBrandDTO newBrandDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        Brand brand = brandService.update(id, newBrandDTO);
        return ResponseEntity.ok(new SuccessfulDTO("Updated brand"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable Long id) {
        Brand brand = brandService.deleteById(id);
        return ResponseEntity.ok(new SuccessfulDTO("Deleted brand"));
    }
}