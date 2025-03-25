package com.enesincekara.account_service.dto;

import java.math.BigDecimal;

public record AccountDto(
        Long id,
        String userEmail,
        String accountType,
        BigDecimal balance
) {
}
