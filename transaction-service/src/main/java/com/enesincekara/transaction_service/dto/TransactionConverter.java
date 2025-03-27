package com.enesincekara.transaction_service.dto;

import com.enesincekara.transaction_service.model.Transaction;

public class TransactionConverter {


    public static TransactionDto toDto(Transaction transaction) {
        return new TransactionDto(
                transaction.getId(),
                transaction.getSenderAccountId(),
                transaction.getReceiverAccountId(),
                transaction.getAmount(),
                transaction.getTransactionType(),
                transaction.getCreatedAt()
        );
    }
}
