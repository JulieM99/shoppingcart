package com.example.shoppingcart.user.authentication;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AuthenticationRequest (
        @NotNull(message = "Email required")
        String email,
        @NotNull(message = "Password required")
        String password
) {
}
