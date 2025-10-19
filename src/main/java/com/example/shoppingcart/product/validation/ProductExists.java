package com.example.shoppingcart.product.validation;

import jakarta.validation.Payload;

public @interface ProductExists {

    String message() default "Product with given id does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
