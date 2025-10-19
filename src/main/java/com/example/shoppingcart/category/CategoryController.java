package com.example.shoppingcart.category;


import com.example.shoppingcart.category.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    public static final String CATEGORY_PATH = "/api/categories";
    private final CategoryService categoryService;

    @Operation(
            summary = "Get category by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Category by ID"
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Integer id) {
        log.info("Getting category with id: {}", id);
        CategoryResponse category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @Operation(
            summary = "Get all categories",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of categories"
                    )
            }
    )
    @GetMapping
    public ResponseEntity<CategoryListResponse> getAllCategories() {
        log.info("Getting all categories");
        var response = categoryService.getCategories();
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Create category",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Category created"
                    )
            }
    )
    @PostMapping
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<CreateCategoryResponse> createCategory(@Valid @RequestBody CreateCategoryRequest request) {
        log.info("Received request to create a new category: {}", request.categoryName());
        CreateCategoryResponse categoryResponse = categoryService.createCategory(request);
        log.info("Created a new category: {}", request.categoryName());
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }

    @Operation(
            summary = "Update category with given id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Category updated"
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable Integer id, @Valid @RequestBody UpdateCategoryRequest request) {
        log.info("Received request to update a category with id: {}", id);
        categoryService.updateCategory(id, request);
        log.info("Updated a category with id: {}", id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Delete category with given id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Category deleted"
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        log.info("Received request to delete a category with id: {}", id);
        categoryService.deleteCategory(id);
        log.info("Deleted a category with id: {}", id);
        return ResponseEntity.ok().build();
    }

//    @PostMapping("/{id}/products")
//    public ResponseEntity<Void> addProduct(@PathVariable Integer id, @Valid @RequestBody AddProductRequest request) {
//
//    }


}
