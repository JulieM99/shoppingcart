package com.example.shoppingcart.category.dto;


import lombok.Builder;

@Builder
public record CreateCategoryResponse (
    Integer id,
    String categoryName
) {}

