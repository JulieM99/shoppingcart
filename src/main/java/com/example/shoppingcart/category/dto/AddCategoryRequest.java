package com.example.shoppingcart.category.dto;

import com.example.shoppingcart.category.validation.CategoryExists;

public record AddCategoryRequest (
        @CategoryExists
        Integer categoryId
)
{ }
