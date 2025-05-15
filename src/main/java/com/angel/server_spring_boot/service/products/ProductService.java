package com.angel.server_spring_boot.service.products;

import com.angel.server_spring_boot.domain.Product;
import com.angel.server_spring_boot.domain.Category;
import com.angel.server_spring_boot.domain.Brand;
import com.angel.server_spring_boot.infrastructure.products.ProductRepository;
import com.angel.server_spring_boot.infrastructure.brands.BrandRepository;
import com.angel.server_spring_boot.infrastructure.categories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.angel.server_spring_boot.dto.products.ProductDTO;
import com.angel.server_spring_boot.dto.products.ProductDetailDTO;

import java.util.List;

import com.angel.server_spring_boot.utils.StringUtils;

// Librerias necesarias para poder usar listas
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Projections
import com.angel.server_spring_boot.projections.products.ProductListProjection;
import com.angel.server_spring_boot.projections.products.ProductDetailProjection;

import com.angel.server_spring_boot.dto.products.ProductPageDTO;
import org.springframework.data.domain.*; // Pageable, PageRequest, Page, Sort

import com.angel.server_spring_boot.projections.products.ProductPageProjection;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, BrandRepository brandRepository) { 
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }

    public List<ProductListProjection> productList() {
        return productRepository.findByDeletedFalse();
    }

    public Product save(ProductDTO productDTO) {
        boolean exists = productRepository.existsByNameAndDeletedFalse(productDTO.getName());
        if (exists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Product with this name already exists");
        }
        String code = StringUtils.generateCode(productDTO.getName());
        List<String> pictures = new ArrayList<>(Arrays.asList("/images/products/picture1.png", "/images/products/picture2.png", "/images/products/picture3.png", "/images/products/picture4.png")); // Listas mutables
        Category category = categoryRepository.findByIdAndDeletedFalse(productDTO.getCategory()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found", new Throwable("Details of the error")));
        Brand brand = brandRepository.findByIdAndDeletedFalse(productDTO.getBrand()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Brand not found", new Throwable("Details of the error")));
        Product product = new Product(
            productDTO.getName(),
            code,
            productDTO.getPrice(),
            productDTO.getShippingCost(),
            productDTO.getDescription(),
            category,
            brand,
            pictures
        );  // Convertimos el DTO a la entidad
        return productRepository.save(product);  // Guardamos en la DB
    }

    public ProductDetailDTO findProduct(Long id) {
        ProductDetailProjection product = productRepository.findProductDetailsById(id);
    
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found", new Throwable("Details of the error"));
        }

        // Cargar imágenes manualmente
        List<String> pictures = productRepository.findPicturesByProductId(id);
    
        return new ProductDetailDTO(product, pictures);
    }

    public ProductPageDTO productPage(int offset, int limit, String search){
        System.out.println("Params "+offset+ " "+limit+" "+search);
        int finalOffset = Math.max(offset - 1, 0);
        Pageable pageable = PageRequest.of(finalOffset, limit, Sort.by("id").descending());

        Page<ProductPageProjection> page;
        if (search != null && !search.isEmpty()) {
            page = productRepository.findByNameContainingIgnoreCase(search, pageable);
        } else {
            page = productRepository.findAllBy(pageable);
        }

        System.out.println("Data"+page.getContent());

        ProductPageDTO products = new ProductPageDTO(page.getContent(), page.getTotalElements(), page.getSize(), page.getNumber());
    
        return products;
    }
}