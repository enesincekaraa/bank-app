package com.enesincekara.user_service.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCreateRequest(
        @Email(message = "Email format is invalid")
        @NotBlank(message = "Email cannot be blank")
        String email,
        @NotBlank(message = "Password cannot be blank")
        String password,
        @NotBlank(message = "Name cannot be blank")
        String name
) {
}
