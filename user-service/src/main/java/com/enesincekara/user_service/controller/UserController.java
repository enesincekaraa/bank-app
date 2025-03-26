package com.enesincekara.user_service.controller;

import com.enesincekara.user_service.dto.UserCreateRequest;
import com.enesincekara.user_service.dto.UserDto;
import com.enesincekara.user_service.dto.UserUpdateRequest;
import com.enesincekara.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.enesincekara.user_service.constants.ApiPaths.*;

@RestController
@RequestMapping(USER_BASE)
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping("/create")
    public UserDto save(@RequestBody @Valid UserCreateRequest request) {
        return userService.saved(request);
    }
    @GetMapping()
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/id/{id}")
    public UserDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/exists")
    public ResponseEntity<Void> userExists(@RequestParam String email) {
        userService.getByEmail(email);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody @Valid UserUpdateRequest request) {
        return userService.update(id, request);
    }




    @GetMapping("/me")
    public ResponseEntity<UserDto> getUserInfo() {
        String email = getEmail();
        UserDto user = userService.getByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/me/update")
    public ResponseEntity<UserDto> updateMyUser(@RequestBody @Valid UserUpdateRequest request) {
        String email = getEmail();
        UserDto updateUser = userService.updateByEmail(email,request);
        return ResponseEntity.ok(updateUser);

    }

    @DeleteMapping("/me/delete")
    public ResponseEntity<Void> deleteMyUser() {
        String email = getEmail();
        userService.deleteByEmail(email);
        return ResponseEntity.noContent().build();
    }





    public String getEmail(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return email;
    }

}
