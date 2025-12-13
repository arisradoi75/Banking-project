package com.exemple.bankingproject.service;

import com.exemple.bankingproject.dto.TransactionRequestDTO;
import com.exemple.bankingproject.dto.TransactionResponseDTO;
import com.exemple.bankingproject.model.Transaction;
import com.exemple.bankingproject.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {
    List<TransactionResponseDTO> getTransactions();
    TransactionResponseDTO saveTransaction(TransactionRequestDTO transaction);
    void deleteTransactionById(Long id);
    TransactionResponseDTO updateTransaction(Long id, TransactionRequestDTO requestDTO);
    List<Transaction> filterTransactions(TransactionType transactionType,
                                         BigDecimal minAmount,
                                         BigDecimal maxAmount,
                                         LocalDateTime beforeDate,
                                         LocalDateTime afterDate);

    Transaction maptoEntity(TransactionRequestDTO dto);
    TransactionResponseDTO maptoDTO(Transaction entity);
}
