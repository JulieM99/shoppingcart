package com.example.shoppingcart.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateCategoryRequest(
   @NotBlank(message = "Category name must be specified")
   String categoryName
) { }
