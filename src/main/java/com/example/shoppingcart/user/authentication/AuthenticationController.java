package com.example.shoppingcart.user.authentication;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    public static final String AUTHENTICATION_PATH = "/api/auth";
    private final AuthenticationService authenticationService;

    @Operation(
            summary = "Register user",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Register successful"
                    )
            }
    )
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerNewUser(@Valid @RequestBody RegisterRequest request) throws BadRequestException {

        RegisterResponse response = authenticationService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping
    @Operation(
            summary = "Authenticate user",
            description = "Authenticates the user using email and password and returns an authentication token.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Authentication successful"
                    )
            }
    )
    public ResponseEntity<AuthenticationResponse> authenticateUser(@Valid @RequestBody AuthenticationRequest request) throws BadRequestException {

        AuthenticationResponse response = authenticationService.authenticate(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/token")
    @Operation(
            summary = "Refresh token",
            description = "Refresh token using Authorization header with 'Bearer ...'",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Authentication successful"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            }
    )
    public ResponseEntity<AuthenticationResponse> authenticateWithToken(@AuthenticationPrincipal UserDetails userDetails) throws BadRequestException {
        AuthenticationResponse response = authenticationService.authenticateWithToken(userDetails);

        return ResponseEntity.ok(response);
    }

}
