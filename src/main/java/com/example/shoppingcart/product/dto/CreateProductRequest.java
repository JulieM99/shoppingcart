package com.example.shoppingcart.product.dto;

import com.example.shoppingcart.category.validation.CategoryExists;
import jakarta.validation.constraints.NotNull;

public record CreateProductRequest (
        @NotNull(message = "Category ID cannot by null")
        @CategoryExists
        Integer categoryId,
        String productName,
        Integer productAmount,
        Double productPrice
) {}
