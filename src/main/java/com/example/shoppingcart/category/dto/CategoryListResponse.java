package com.example.shoppingcart.category.dto;

import java.util.List;

public record CategoryListResponse (
        List<CategoryResponse> categories
) { }
