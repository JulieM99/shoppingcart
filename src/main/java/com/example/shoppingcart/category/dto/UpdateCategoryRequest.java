package com.example.shoppingcart.category.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UpdateCategoryRequest (
   String newCategoryName
){ }
