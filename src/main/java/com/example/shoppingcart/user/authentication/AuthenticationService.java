package com.example.shoppingcart.user.authentication;

import com.example.shoppingcart.config.util.JwtUtils;
import com.example.shoppingcart.user.User;
import com.example.shoppingcart.user.UserMapper;
import com.example.shoppingcart.user.UserRepository;
import com.example.shoppingcart.user.dto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse register(@Valid RegisterRequest request) throws BadRequestException {
        Optional<User> userOptional = userRepository.findByEmail(request.email());

        if (userOptional.isPresent()) {
            throw new BadRequestException("User with this e-mail already exists");
        }

        User user = userMapper.toEntity(request);

        String encodedPassword = passwordEncoder.encode(request.password());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        var jwtToken = jwtUtils.generateToken(user);
        UserDto userDto = userMapper.toDto(user);

        return new RegisterResponse(jwtToken, userDto);
    }

    public AuthenticationResponse authenticate(@Valid AuthenticationRequest request) throws BadRequestException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        User user = userRepository.findByEmailOrThrow(request.email());

        return new AuthenticationResponse(jwtUtils.generateToken(user), userMapper.toDto(user));
    }

    public AuthenticationResponse authenticateWithToken(UserDetails userDetails) throws BadRequestException {

        User user = userRepository.findByEmailOrThrow(userDetails.getUsername()); // Username is email

        return new AuthenticationResponse(jwtUtils.generateToken(user), userMapper.toDto(user));
    }
}
