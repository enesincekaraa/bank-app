package com.enesincekara.user_service.service.impl;

import com.enesincekara.user_service.dto.UserCreateRequest;
import com.enesincekara.user_service.dto.UserDto;
import com.enesincekara.user_service.dto.UserMapper;
import com.enesincekara.user_service.dto.UserUpdateRequest;
import com.enesincekara.user_service.model.User;
import com.enesincekara.user_service.repository.UserRepository;
import com.enesincekara.user_service.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService , UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDto saved(UserCreateRequest request) {
        User user = new User();
        user.setEmail(request.email());
        user.setName(request.name());


        String encodedPassword = passwordEncoder.encode(request.password());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return UserMapper.toUserDto(user);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserMapper::toUserDto).toList();
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserDto update(Long id, UserUpdateRequest request) {
        User user = findUserById(id);
        user.setEmail(request.email());
        user.setName(request.name());
        return UserMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public UserDto findById(Long id) {
        User user = findUserById(id);
        return UserMapper.toUserDto(user);
    }

    @Override
    public boolean checkPassword(String email, String rawPassword) {
        User user = findUserByEmail(email);
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    @Override
    public UserDto findByEmail(String email) {
        User user = findUserByEmail(email);
        return new UserDto(user.getId(), user.getEmail(), user.getName());
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findUserByEmail(email);

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList()
        );
    }


    @Override
    public UserDto getByEmail(String email) {
        User user = getUserByEmail(email);
        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDto updateByEmail(String email, UserUpdateRequest request) {
       User user = findUserByEmail(email);
       user.setName(request.name());
       user.setEmail(request.email());

       userRepository.save(user);
       return UserMapper.toUserDto(user);
    }

    @Override
    public void deleteByEmail(String email) {
        User user = findUserByEmail(email);
        userRepository.delete(user);
    }


    private  User findUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id:" + id));
        return user;
    }


    private User findUserByEmail(String email){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found with email:" + email));
        return user;
    }

    private User getUserByEmail(String email){
      User user = userRepository.getUserByEmail(email).orElseThrow(() -> new RuntimeException("User not found with email : " + email));
       return user;
    }
}
