package com.enesincekara.transaction_service.dto;

import com.enesincekara.transaction_service.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDto(
        Long id,
        String senderAccountId,
        String receiverAccountId,
        BigDecimal amount,
        TransactionType transactionType,
        LocalDateTime createdAt
) {

}
