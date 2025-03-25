package com.enesincekara.user_service.service;

import com.enesincekara.user_service.dto.UserCreateRequest;
import com.enesincekara.user_service.dto.UserDto;
import com.enesincekara.user_service.dto.UserUpdateRequest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    UserDto saved(UserCreateRequest request);
    List<UserDto> findAll();
    void delete(Long id);
    UserDto update(Long id, UserUpdateRequest request);
    UserDto findById(Long id);

    boolean checkPassword(String email, String rawPassword);
    UserDto findByEmail(String email);

    UserDetails loadUserByUsername(String email);

    UserDto getByEmail(String email);

    UserDto updateByEmail(String email,  UserUpdateRequest request);

    void deleteByEmail(String email);
}
