package com.example.shoppingcart.category;

import com.example.shoppingcart.category.dto.*;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CreateCategoryRequest request);

    CategoryResponse toCategoryResponse(Category category);

    CreateCategoryResponse toCreateCategoryResponse(Category category);

    List<CategoryResponse> toDtoList(List<Category> categories);

    default CategoryListResponse toListResponse(List<Category> categories) {
        return new CategoryListResponse(toDtoList(categories));
    }
}
