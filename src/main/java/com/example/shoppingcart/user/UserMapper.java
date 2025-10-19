package com.example.shoppingcart.user;

import com.example.shoppingcart.user.authentication.RegisterRequest;
import com.example.shoppingcart.user.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {


   User toEntity(RegisterRequest request);

   List<UserDto> toDtoList(List<User> list);

   UserDto toDto(User user);

}
