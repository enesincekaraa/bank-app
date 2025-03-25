package com.enesincekara.account_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AccountCreateRequest(
        @NotBlank
        String userEmail,
        @NotBlank
        String accountType,
        @NotNull
        BigDecimal balance
) {
}
