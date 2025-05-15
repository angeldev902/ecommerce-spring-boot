package com.angel.server_spring_boot.controllers.products;

import com.angel.server_spring_boot.domain.Product;
import com.angel.server_spring_boot.service.products.ProductService;
import com.angel.server_spring_boot.service.externalservices.CurrencyService;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.angel.server_spring_boot.dto.products.ProductDTO;
import com.angel.server_spring_boot.dto.global.SuccessfulDTO;
import com.angel.server_spring_boot.dto.products.ProductDetailDTO;
import com.angel.server_spring_boot.dto.products.ProductPageDTO;

import java.util.List;

//Projections
import com.angel.server_spring_boot.projections.products.ProductListProjection;
import com.angel.server_spring_boot.projections.products.ProductDetailProjection;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CurrencyService currencyService;

    public ProductController(ProductService productService, CurrencyService currencyService) {
        this.productService = productService;
        this.currencyService = currencyService;
    }

    @GetMapping("/dollar-price")
    public ResponseEntity<?> getDollarPrice() {
        try {
            Map<String, Object> response = currencyService.getDollarPrice();
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/page")
    public ResponseEntity<?> getProductsPage(
        @RequestParam(defaultValue = "0") int offset,
        @RequestParam(defaultValue = "10") int limit,
        @RequestParam(required = false) String search
    ) {
        ProductPageDTO page = productService.productPage(offset, limit, search);
        return ResponseEntity.ok(page);
    }

    @GetMapping
    public List<ProductListProjection> getProducts() {
        return productService.productList();
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTO productDTO,  BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        Product product = productService.save(productDTO);
        return ResponseEntity.ok(new SuccessfulDTO("Created product"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailDTO> getProduct(@PathVariable Long id) {
        ProductDetailDTO product = productService.findProduct(id);
        return ResponseEntity.ok(product);
    }
}