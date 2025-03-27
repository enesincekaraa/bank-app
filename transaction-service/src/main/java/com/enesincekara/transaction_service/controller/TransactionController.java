package com.enesincekara.transaction_service.controller;

import com.enesincekara.transaction_service.dto.TransactionCreateRequest;
import com.enesincekara.transaction_service.dto.TransactionDto;
import com.enesincekara.transaction_service.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/create")
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody @Valid TransactionCreateRequest request) {
        String senderEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        TransactionDto transaction = transactionService.createTransaction(senderEmail, request);
        return ResponseEntity.ok(transaction);
    }

//    @GetMapping
//    public ResponseEntity<List<TransactionDto>> getAllMyTransactions() {
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//        return ResponseEntity.ok(transactionService.getAllByUserEmail(email));
//    }
}
