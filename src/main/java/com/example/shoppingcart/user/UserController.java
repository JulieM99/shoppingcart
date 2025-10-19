package com.example.shoppingcart.user;


import com.example.shoppingcart.shoppinglist.dto.ShoppingListResponse;
import com.example.shoppingcart.user.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    public static final String USERS_PATH = "/api/users";
    private final UserService userService;

    @Operation(
            summary = "Get user's shopping lists",
            responses = {
                    @ApiResponse(
                            responseCode = "200"
                    )
            }
    )
    @GetMapping("/{id}/shoppinglists")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<ShoppingListResponse>> getShoppingLists(@PathVariable Integer id) {
        log.info("Get user's {} shopping lists", id);

        List<ShoppingListResponse> response = userService.getUserShoppingLists(id);

        return ResponseEntity.ok(response);
    }


    @Operation(
            summary = "Get user by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Get user by ID"
                    )
            }
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        log.info("Getting user: {}", id);

        var response = userService.getUserById(id);

        return ResponseEntity.ok(response);
    }


    @Operation(
            summary = "Get users",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Services retrieved"
                    )
            }
    )
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserDto>> getUsers() {
        log.info("Getting users");

        var response = userService.getUsers();

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Delete user with given ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Delete user"
                    )
            }
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Void> getUsers(@PathVariable Integer id) {
        log.info("Deleting user with id: {}", id);

        userService.deleteUser(id);

        return ResponseEntity.ok().build();

    }


}
