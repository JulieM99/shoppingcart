package com.example.shoppingcart.common;

import org.springframework.data.domain.Page;

public record PageInfo(
        Long totalPages,
        Long totalElements,
        Long currentPage,
        Long numberOfElements
) {
    public static PageInfo of(Page<?> page) {
        return new PageInfo(
                (long) page.getTotalPages(),
                page.getTotalElements(),
                (long) page.getNumber(),
                (long) page.getNumberOfElements()
        );
    }
}