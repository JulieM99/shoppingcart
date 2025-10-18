package com.example.shoppingcart.category;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface CategoryRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {

    default Category findByIdOrThrow(Integer id) throws ChangeSetPersister.NotFoundException {
        return findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }



}
