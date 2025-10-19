package com.example.shoppingcart.product.dto;

import com.example.shoppingcart.product.validation.ProductExists;

public record AddProductRequest(
        @ProductExists
        Integer productId
) {
}
