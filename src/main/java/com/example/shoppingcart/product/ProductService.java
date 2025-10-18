package com.example.shoppingcart.product;

import com.example.shoppingcart.category.Category;
import com.example.shoppingcart.category.CategoryMapper;
import com.example.shoppingcart.category.CategoryRepository;
import com.example.shoppingcart.product.dto.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Transactional
    public CreateProductResponse createProduct(@Valid CreateProductRequest request) {

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        Product newProduct = new Product();
        newProduct.setCategory(category);
        newProduct.setProductAmount(request.productAmount());
        newProduct.setProductName(request.productName());
        newProduct.setProductPrice(request.productPrice());

        productRepository.save(newProduct);
        return productMapper.toCreateProductResponse(newProduct);
    }

    public ProductResponse getProductById(Integer id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return productMapper.toProductResponse(product);
    }

    public ProductListResponse getProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toListResponse(products);
    }

    @Transactional
    public void deleteProduct(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }

    @Transactional
    public void updateProduct(Integer id, @Valid UpdateProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setProductName(request.newProductName());
        product.setProductPrice(request.newProductPrice());
        product.setProductAmount(request.newProductAmount());

        productRepository.save(product);
    }

}
