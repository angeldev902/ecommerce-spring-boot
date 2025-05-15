package com.angel.server_spring_boot.service.categories;

import com.angel.server_spring_boot.domain.Category;
import com.angel.server_spring_boot.infrastructure.categories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.angel.server_spring_boot.dto.categories.CategoryDTO;

import java.util.List;

import com.angel.server_spring_boot.utils.StringUtils;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) { 
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findByDeletedFalse();
    }

    public Category findOneCategory(Long id) {
        Category category = categoryRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found", new Throwable("Details of the error")));
        return category;
    }

    public Category save(CategoryDTO categoryDTO) {
        boolean exists = categoryRepository.existsByNameAndDeletedFalse(categoryDTO.getName());
        if (exists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category with this name already exists");
        }
        String code = StringUtils.generateCode(categoryDTO.getName());
        Category parentCategory = null;
        if(categoryDTO.getParentCategory() != null){
            parentCategory = categoryRepository.findByIdAndDeletedFalse(categoryDTO.getParentCategory()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parent category not found", new Throwable("Details of the error")));
        }
        Category category = new Category(categoryDTO.getName(), code, parentCategory);
        return categoryRepository.save(category);
    }

    public Category update(Long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found", new Throwable("Details of the error")));
        category.setName(categoryDTO.getName());
        String code = StringUtils.generateCode(categoryDTO.getName());
        category.setCode(code);

        if(categoryDTO.getParentCategory() != null){
            Category parentCategory = categoryRepository.findByIdAndDeletedFalse(categoryDTO.getParentCategory()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parent category not found", new Throwable("Details of the error")));
            category.setParentCategory(parentCategory);
        }

        return categoryRepository.save(category);
    }

    public Category deleteById(Long id) {
        Category category = categoryRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found", new Throwable("Details of the error")));
        category.setDeleted(true);
        return categoryRepository.save(category);
    }
}