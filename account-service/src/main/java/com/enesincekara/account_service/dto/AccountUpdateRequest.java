package com.enesincekara.account_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AccountUpdateRequest(
        @NotBlank
        String accountType,
        @NotNull
        BigDecimal balance
) {
}
