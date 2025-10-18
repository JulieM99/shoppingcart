package com.example.shoppingcart.shoppinglist;

import com.example.shoppingcart.category.Category;
import com.example.shoppingcart.category.CategoryRepository;
import com.example.shoppingcart.shoppinglist.dto.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final CategoryRepository categoryRepository;
    private final ShoppingListMapper  shoppingListMapper;

    @Transactional
    public CreateShoppingListResponse createShoppingList(CreateShoppingListRequest createShoppingListRequest) {

        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setListName(createShoppingListRequest.listName());
        shoppingListRepository.save(shoppingList);
        return new CreateShoppingListResponse(shoppingList.getId(), shoppingList.getListName());
    }

    public ShoppingListResponse getShoppingListById(Integer id) {
        ShoppingList shoppingList = shoppingListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ShoppingList not found"));

        return  shoppingListMapper.toShoppingListResponse(shoppingList);
    }

    public ShoppingListListsResponse getShoppingLists() {
        List<ShoppingList> shoppingList = shoppingListRepository.findAll();
        return shoppingListMapper.toListResponse(shoppingList);
    }

    @Transactional
    public void updateShoppingList(Integer id, @Valid UpdateShoppingListRequest request) {
        ShoppingList shoppingList = shoppingListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ShoppingList not found"));

        shoppingList.setListName(request.newShoppingListName());
        shoppingListRepository.save(shoppingList);
    }

    @Transactional
    public void deleteShoppingList(Integer id) {
        ShoppingList shoppingList = shoppingListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ShoppingList not found"));
        shoppingListRepository.delete(shoppingList);
    }

    @Transactional
    public void addCategoryToShoppingList(Integer shoppingListId, Integer categoryId) {
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping list not found"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        shoppingList.addCategory(category);

        shoppingListRepository.save(shoppingList);
    }


}
