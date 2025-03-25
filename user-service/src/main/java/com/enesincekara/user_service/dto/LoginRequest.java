package com.enesincekara.user_service.dto;

import jakarta.validation.constraints.Email;

public record LoginRequest(
        @Email
        String email,
        String password
) {
}
