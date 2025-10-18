package com.example.shoppingcart.shoppinglist.dto;

import java.util.List;

public record ShoppingListListsResponse (
        List<ShoppingListResponse> shoppingListLists
){
}
