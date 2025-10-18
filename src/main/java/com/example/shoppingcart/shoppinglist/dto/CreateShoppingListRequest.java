package com.example.shoppingcart.shoppinglist.dto;

import jakarta.validation.constraints.NotNull;

public record CreateShoppingListRequest(
        @NotNull
        String listName
) {
}
