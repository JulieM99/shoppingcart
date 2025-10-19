package com.example.shoppingcart.user.authentication;

import com.example.shoppingcart.user.dto.UserDto;

public record RegisterResponse (
        String token,
        UserDto user
){
}
