package com.example.shoppingcart.shoppinglist;


import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Integer>, JpaSpecificationExecutor<ShoppingList> {

    default ShoppingList findByIdOrThrow(Integer id) throws ChangeSetPersister.NotFoundException {
        return findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }
}
