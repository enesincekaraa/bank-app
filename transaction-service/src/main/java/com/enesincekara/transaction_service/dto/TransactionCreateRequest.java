package com.enesincekara.transaction_service.dto;

import com.enesincekara.transaction_service.model.TransactionType;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;

public record TransactionCreateRequest(
        @NotNull
        String receiverAccountId,
        @NotNull
        BigDecimal amount,
        @NotNull
        TransactionType transactionType
) {
}
