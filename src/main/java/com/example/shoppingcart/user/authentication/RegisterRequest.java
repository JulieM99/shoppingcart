package com.example.shoppingcart.user.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest (

        @NotNull(message = "Email required")
        @Email(message = "Invalid email format")
        String email,

        @NotNull(message = "Password required")
        @Size(min = 4, message = "Password must be at least 4 characters long")
        String password,

        @NotNull(message = "First name required")
        @NotEmpty(message = "First name required")
        String firstName,

        @NotNull(message = "Last name required")
        @NotEmpty(message = "Last name required")
        String lastName

) {
}
