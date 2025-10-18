package com.example.shoppingcart.product.dto;

public record UpdateProductRequest (
    String newProductName,
    Double newProductPrice,
    Integer newProductAmount
) {
}
