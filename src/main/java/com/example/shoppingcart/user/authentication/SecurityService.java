package com.example.shoppingcart.user.authentication;

import com.example.shoppingcart.shoppinglist.ShoppingList;
import com.example.shoppingcart.shoppinglist.ShoppingListService;
import com.example.shoppingcart.shoppinglist.dto.ShoppingListResponse;
import com.example.shoppingcart.user.UserDetailsInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SecurityService {

    AuthenticationUtils authenticationUtils;
    ShoppingListService shoppingListService;

    public boolean isShoppingListOwner(Integer shoppingListId) {
        UserDetailsInfo user = authenticationUtils.getAuthenticatedUser();
        if (isNotAuthorized(user)) {
            return false;
        }

        try {
            ShoppingListResponse shoppingList = shoppingListService.getShoppingListById(shoppingListId);
            return shoppingList.userId().equals(user.getId());
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUser(Long userId) {
        UserDetailsInfo user = authenticationUtils.getAuthenticatedUser();
        if (isNotAuthorized(user)) {
            return false;
        }

        return user.getId().equals(userId);
    }

    private boolean isNotAuthorized(UserDetailsInfo user) {
        return user == null;
    }
}
