package com.example.shoppingcart.shoppinglist.dto;

import lombok.Builder;

@Builder
public record ShoppingListResponse (
        Integer id,
        Integer userId,
        String listName
){

}
