package com.example.shoppingcart.shoppinglist;

import com.example.shoppingcart.category.dto.AddCategoryRequest;
import com.example.shoppingcart.shoppinglist.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shoppinglist")
@RequiredArgsConstructor
@Slf4j
public class ShoppingListController {

    public static final String SHOPPINGLIST_PATH = "/api/shoppinglist";
    private final ShoppingListService shoppingListService;


    @Operation(
            summary = "Create shopping list",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Create new shopping list"
                    )
            }
    )
    @PostMapping
    public ResponseEntity<CreateShoppingListResponse> createShoppingList(@Valid @RequestBody CreateShoppingListRequest request) {
        log.info("Creating new shopping list with name: {}", request.listName());
        CreateShoppingListResponse shoppingListResponse = shoppingListService.createShoppingList(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(shoppingListResponse);

    }

    @Operation(
            summary = "Get shopping list by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Shopping list by ID"
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ShoppingListResponse> getShoppingLisTById(@PathVariable Integer id) {
        log.info("Getting shopping list with id: {}", id);
        ShoppingListResponse shoppingListResponse = shoppingListService.getShoppingListById(id);
        return ResponseEntity.ok(shoppingListResponse);
    }

    @Operation(
            summary = "Get all shopping lists",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "All shopping lists"
                    )
            }
    )
    @GetMapping
    public ResponseEntity<ShoppingListListsResponse> getAllShoppingLists() {
        log.info("Getting all shopping lists");
        ShoppingListListsResponse shoppingListListsResponse = shoppingListService.getShoppingLists();
        return ResponseEntity.ok(shoppingListListsResponse);
    }
    @Operation(
            summary = "Update shopping list with given ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Update shopping list"
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateShoppingList(@PathVariable Integer id, @Valid @RequestBody UpdateShoppingListRequest request) {
        log.info("Updating shopping list with id: {}", id);
        shoppingListService.updateShoppingList(id, request);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Delete shopping list with given ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Delete shopping list"
                    )
            }
    )
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteShoppingList(@PathVariable Integer id) {
        log.info("Deleting shopping list with id: {}", id);
        shoppingListService.deleteShoppingList(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/categories")
    public ResponseEntity<?> addCategoryToList(@PathVariable Integer id, @Valid @RequestBody AddCategoryRequest request) {
        shoppingListService.addCategoryToShoppingList(id, request.categoryId());
        return ResponseEntity.ok().build();
    }
}
