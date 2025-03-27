package com.enesincekara.transaction_service.dto;

import java.math.BigDecimal;

public record AccountDto(
        String id,
        String accountType,
        BigDecimal balance
) {
}
