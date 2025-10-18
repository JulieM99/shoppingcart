package com.example.shoppingcart.product.dto;

public record ProductResponse(
    Integer id,
    String productName,
    Integer categoryId,
    Double productPrice,
    Integer productAmount
) {
}
