package com.exemple.bankingproject.controller;

import com.exemple.bankingproject.model.Transaction;
import com.exemple.bankingproject.model.TransactionType;
import com.exemple.bankingproject.service.TransactionService;
import com.exemple.bankingproject.service.TransactionServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
        private final TransactionService transactionService;
    private final TransactionServiceImpl transactionServiceImpl;

    public TransactionController(TransactionService transactionService, TransactionServiceImpl transactionServiceImpl) {
            this.transactionService = transactionService;
        this.transactionServiceImpl = transactionServiceImpl;
    }

        @GetMapping
        public List<Transaction> getAllTransactions() {
            return transactionService.getTransactions();
        }

        @PostMapping
        public Transaction createTransaction(@RequestBody Transaction transaction) {
            return transactionService.saveTransaction(transaction);
        }

        @PutMapping("/{id}")
        public Transaction updateTransaction(@PathVariable Long id,@RequestBody Transaction transaction) {
            transaction.setId(id);
            return transactionService.updateTransaction(id, transaction);
        }

        @DeleteMapping("/{id}")
        public void deleteTransaction(@PathVariable Long id) {
            transactionService.deleteTransactionById(id);
        }

        @GetMapping("/filter")
        public List<Transaction> getFilteredTransactions(@RequestParam(required = false) TransactionType type,
                                                         @RequestParam(required = false) BigDecimal minAmount,
                                                         @RequestParam(required = false) BigDecimal maxAmount,
                                                         @RequestParam(required = false) LocalDateTime beforeDate,
                                                         @RequestParam(required = false) LocalDateTime afterDate
        )
        {
            return transactionServiceImpl.filterTransactions(type, minAmount, maxAmount, beforeDate, afterDate);
        }
}
