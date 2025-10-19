package com.example.shoppingcart.user.dto;


import com.example.shoppingcart.user.authentication.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Role role;
}
