package com.enesincekara.transaction_service.service;

import com.enesincekara.transaction_service.dto.TransactionCreateRequest;
import com.enesincekara.transaction_service.dto.TransactionDto;

import java.util.List;

public interface TransactionService {

    TransactionDto createTransaction(String senderEmail, TransactionCreateRequest request);
    List<TransactionDto> getAllTransactions();
}
