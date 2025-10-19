package com.example.shoppingcart.product.validation;

import com.example.shoppingcart.category.CategoryRepository;
import com.example.shoppingcart.product.ProductRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProductExistsValidator implements ConstraintValidator<ProductExists, Integer> {

    private final ProductRepository productRepository;

    public ProductExistsValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean isValid(Integer productId, ConstraintValidatorContext constraintValidatorContext) {
        return  productRepository.existsById(productId);
    }

}
