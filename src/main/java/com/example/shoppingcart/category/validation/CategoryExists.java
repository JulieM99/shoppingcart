package com.example.shoppingcart.category.validation;

import jakarta.validation.Payload;

public @interface CategoryExists {

    String message() default "Category with given id does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
