package com.example.shoppingcart.user.authentication;

import com.example.shoppingcart.user.dto.UserDto;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AuthenticationResponse (
        String token,
        UserDto user
) {
}
