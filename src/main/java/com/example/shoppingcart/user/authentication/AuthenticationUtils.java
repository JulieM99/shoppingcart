package com.example.shoppingcart.user.authentication;

import com.example.shoppingcart.user.UserDetailsInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUtils {

    public UserDetailsInfo getAuthenticatedUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return (UserDetailsInfo) authentication.getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

    public Integer getAuthenticatedUserId() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsInfo userDetails = (UserDetailsInfo) authentication.getPrincipal();
            return userDetails.getId();
        } catch (Exception e) {
            return null;
        }
    }
}
