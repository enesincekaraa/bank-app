package com.enesincekara.transaction_service.repository;

import com.enesincekara.transaction_service.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findBySenderAccountIdOrReceiverAccountId(String senderId, String receiverId);
}
