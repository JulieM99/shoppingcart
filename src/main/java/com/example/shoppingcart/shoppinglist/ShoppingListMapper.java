package com.example.shoppingcart.shoppinglist;


import com.example.shoppingcart.shoppinglist.dto.CreateShoppingListRequest;
import com.example.shoppingcart.shoppinglist.dto.CreateShoppingListResponse;
import com.example.shoppingcart.shoppinglist.dto.ShoppingListListsResponse;
import com.example.shoppingcart.shoppinglist.dto.ShoppingListResponse;
import org.mapstruct.Mapper;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShoppingListMapper {

    ShoppingList toEntity(CreateShoppingListRequest request);

    ShoppingListResponse toShoppingListResponse(ShoppingList shoppingList);

    CreateShoppingListResponse toCreateShoppingListResponse(ShoppingList shoppingList);

    List<ShoppingListResponse> toDtoList(List<ShoppingList> shoppingList);

    default ShoppingListListsResponse toListResponse(List<ShoppingList> shoppingList) {
        return new ShoppingListListsResponse(toDtoList(shoppingList));
    }
}
