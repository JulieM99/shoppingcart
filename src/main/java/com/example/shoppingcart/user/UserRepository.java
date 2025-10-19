package com.example.shoppingcart.user;

import org.apache.coyote.BadRequestException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    Optional<User> findByEmail(String email);

    default User findByEmailOrThrow(String email) throws BadRequestException {
        return findByEmail(email)
                .orElseThrow(() -> new BadRequestException("User with email: " + email + " not found"));
    }
}
