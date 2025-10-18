package com.example.shoppingcart.category;

import com.example.shoppingcart.category.dto.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    public CreateCategoryResponse createCategory(@Valid CreateCategoryRequest request) {
        Category newCategory = categoryMapper.toEntity(request);
        categoryRepository.save(newCategory);
        return categoryMapper.toCreateCategoryResponse(newCategory);
    }


    public CategoryResponse getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return categoryMapper.toCategoryResponse(category);
    }

    public CategoryListResponse getCategories() {
        List<Category> categories =  categoryRepository.findAll();
        return categoryMapper.toListResponse(categories);
    }

    @Transactional
    public void deleteCategory(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        categoryRepository.delete(category);
    }

    @Transactional
    public void updateCategory(Integer id, @Valid UpdateCategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setCategoryName(request.newCategoryName());
        categoryRepository.save(category);
    }
}
