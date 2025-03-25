package com.enesincekara.user_service.dto;


public record UserDto(
        Long id,
        String email,
        String name
) {
}
