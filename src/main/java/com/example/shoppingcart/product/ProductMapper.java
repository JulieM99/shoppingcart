package com.example.shoppingcart.product;

import com.example.shoppingcart.product.dto.CreateProductRequest;
import com.example.shoppingcart.product.dto.CreateProductResponse;
import com.example.shoppingcart.product.dto.ProductListResponse;
import com.example.shoppingcart.product.dto.ProductResponse;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(CreateProductRequest request);

    ProductResponse toProductResponse(Product product);

    CreateProductResponse toCreateProductResponse(Product product);

    List<ProductResponse> toDtoList(List<Product> products);

    default ProductListResponse toListResponse(List<Product> products) {
        return new ProductListResponse(toDtoList(products));
    }
}
