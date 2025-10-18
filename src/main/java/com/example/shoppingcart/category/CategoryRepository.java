package com.example.shoppingcart.category;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {

    default Category findByIdOrThrow(Integer id) throws ChangeSetPersister.NotFoundException {
        return findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }



}
