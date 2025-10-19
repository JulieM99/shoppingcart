package com.example.shoppingcart.user;

import com.example.shoppingcart.shoppinglist.ShoppingList;
import com.example.shoppingcart.shoppinglist.ShoppingListMapper;
import com.example.shoppingcart.shoppinglist.ShoppingListRepository;
import com.example.shoppingcart.shoppinglist.dto.ShoppingListResponse;
import com.example.shoppingcart.user.dto.UserDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ShoppingListRepository shoppingListRepository;
    private final ShoppingListMapper shoppingListMapper;

    public List<ShoppingListResponse> getUserShoppingLists(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        List<ShoppingList> shoppingLists = user.getShoppingLists();
        List<ShoppingListResponse> shoppingListResponse = shoppingListMapper.toDtoList(shoppingLists);

        return shoppingListResponse;


    }

    public List<UserDto> getUsers() {

        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = userMapper.toDtoList(users);
        return userDtos;

    }

    public UserDto getUserById(Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        UserDto userDto = userMapper.toDto(user);

        return userDto;
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        userRepository.delete(user);
    }
}
