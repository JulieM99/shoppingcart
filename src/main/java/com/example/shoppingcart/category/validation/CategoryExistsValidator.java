package com.example.shoppingcart.category.validation;

import com.example.shoppingcart.category.CategoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CategoryExistsValidator implements ConstraintValidator<CategoryExists, Integer> {

    private final CategoryRepository  categoryRepository;

    public CategoryExistsValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;

    }
    @Override
    public boolean isValid(Integer categoryId, ConstraintValidatorContext constraintValidatorContext) {
        return  categoryRepository.existsById(categoryId);
    }
}
