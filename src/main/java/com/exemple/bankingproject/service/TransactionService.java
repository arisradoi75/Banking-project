package com.exemple.bankingproject.service;

import com.exemple.bankingproject.model.Transaction;
import com.exemple.bankingproject.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactions();
    Transaction saveTransaction(Transaction transaction);
    void deleteTransactionById(Long id);
    Transaction updateTransaction(Long id, Transaction transaction);
    List<Transaction> filterTransactions(TransactionType transactionType,
                                         BigDecimal minAmount,
                                         BigDecimal maxAmount,
                                         LocalDateTime beforeDate,
                                         LocalDateTime afterDate);

}
