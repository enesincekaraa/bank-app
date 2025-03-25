package com.enesincekara.user_service.dto;

import com.enesincekara.user_service.model.User;

public class UserMapper {

    public static UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getName());
    }

    public static User toUser(UserCreateRequest request) {
        User user = new User();
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setName(request.name());
        return user;
    }
}
