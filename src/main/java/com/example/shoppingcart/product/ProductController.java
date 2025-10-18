package com.example.shoppingcart.product;


import com.example.shoppingcart.product.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    public static final String PRODUCT_PATH = "/api/products";
    private final ProductService productService;

    @Operation(
            summary = "Get product by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product by ID"
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Integer id) {
        log.info("Getting product with id: {}", id);
        ProductResponse product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @Operation(
            summary = "Get all products",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "All products"
                    )
            }
    )
    @GetMapping
    public ResponseEntity<ProductListResponse> getAllProducts() {
        log.info("Getting all products");
        var response = productService.getProducts();
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Create product",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Product created"
                    )
            }
    )
    @PostMapping
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<CreateProductResponse> createProduct(@Valid @RequestBody CreateProductRequest request) {
        log.info("Received request to create new product: {}", request.productName());
        CreateProductResponse productResponse = productService.createProduct(request);
        log.info("Created new product: {}", productResponse.productName());
        return  ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    @Operation(
            summary = "Update product with given id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product updated"
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Integer id, @Valid @RequestBody UpdateProductRequest request) {
        log.info("Received request to update a product with id: {}", id);
        productService.updateProduct(id, request);
        log.info("Updated a product with id: {}", id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Delete product with given id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product deleted"
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        log.info("Received request to delete a product with id: {}", id);
        productService.deleteProduct(id);
        log.info("Deleted a product with id: {}", id);
        return ResponseEntity.ok().build();
    }


}
