package com.angel.server_spring_boot.controllers.categories;

import com.angel.server_spring_boot.domain.Category;
import com.angel.server_spring_boot.service.categories.CategoryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.angel.server_spring_boot.dto.categories.CategoryDTO;
import com.angel.server_spring_boot.dto.global.SuccessfulDTO;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }
    
    @PostMapping
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDTO categoryDTO,  BindingResult result) {
        if (result.hasErrors()) return ResponseEntity.badRequest().body(result.getAllErrors());
        Category category = categoryService.save(categoryDTO);
        return ResponseEntity.ok(new SuccessfulDTO("Created category"));
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Long id) {
        return categoryService.findOneCategory(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDTO categoryDTO, BindingResult result) {
        if (result.hasErrors()) return ResponseEntity.badRequest().body(result.getAllErrors());
        Category category = categoryService.update(id, categoryDTO);
        return ResponseEntity.ok(new SuccessfulDTO("Updated category"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        Category category = categoryService.deleteById(id);
        return ResponseEntity.ok(new SuccessfulDTO("Deleted category"));
    }
}