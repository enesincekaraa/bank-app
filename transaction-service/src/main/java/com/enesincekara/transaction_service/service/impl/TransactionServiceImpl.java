package com.enesincekara.transaction_service.service.impl;

import com.enesincekara.transaction_service.client.AccountServiceClient;
import com.enesincekara.transaction_service.dto.TransactionCreateRequest;
import com.enesincekara.transaction_service.dto.TransactionDto;
import com.enesincekara.transaction_service.model.Transaction;
import com.enesincekara.transaction_service.model.TransactionType;
import com.enesincekara.transaction_service.repository.TransactionRepository;
import com.enesincekara.transaction_service.service.TransactionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountServiceClient accountServiceClient;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountServiceClient accountServiceClient) {
        this.transactionRepository = transactionRepository;
        this.accountServiceClient = accountServiceClient;
    }


    @Override
    public TransactionDto createTransaction(String senderEmail, TransactionCreateRequest request) {
        if (request.transactionType().equals(TransactionType.TRANSFER)
                || request.transactionType().equals(TransactionType.WITHDRAW)){
            accountServiceClient.updateBalance(senderEmail,"SAVINGS",request.amount().negate());
        }

        if (request.transactionType().equals(TransactionType.TRANSFER)
                || request.transactionType().equals(TransactionType.DEPOSIT)){
            accountServiceClient.updateBalance(request.receiverAccountId(),"SAVINGS",request.amount());
        }

        Transaction transaction = new Transaction();
        transaction.setSenderAccountId(senderEmail);
        transaction.setReceiverAccountId(request.receiverAccountId());
        transaction.setAmount(request.amount());
        transaction.setTransactionType(request.transactionType());
        transaction.setCreatedAt(LocalDateTime.now());

        Transaction saved = transactionRepository.save(transaction);

        return new TransactionDto(
                saved.getId(),
                saved.getSenderAccountId(),
                saved.getReceiverAccountId(),
                saved.getAmount(),
                saved.getTransactionType(),
                saved.getCreatedAt()
        );

    }

    @Override
    public List<TransactionDto> getAllTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(tr -> new TransactionDto(
                        tr.getId(),
                        tr.getSenderAccountId(),
                        tr.getReceiverAccountId(),
                        tr.getAmount(),
                        tr.getTransactionType(),
                        tr.getCreatedAt()
                ))
                .toList();

    }
}
