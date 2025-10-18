package com.example.shoppingcart.product.dto;

import java.util.List;

public record ProductListResponse (
        List<ProductResponse> products
) {
}
