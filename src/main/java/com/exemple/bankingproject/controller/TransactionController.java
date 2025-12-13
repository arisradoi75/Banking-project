package com.exemple.bankingproject.controller;

import com.exemple.bankingproject.dto.TransactionRequestDTO;
import com.exemple.bankingproject.dto.TransactionResponseDTO;
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


    public TransactionController(TransactionService transactionService) {
            this.transactionService = transactionService;
    }

        @GetMapping
        public List<TransactionResponseDTO> getAllTransactions() {
            return transactionService.getTransactions();
        }

        @PostMapping
        public TransactionResponseDTO createTransaction(@RequestBody TransactionRequestDTO requestDTO) {
                return transactionService.saveTransaction(requestDTO);
        }

        @PutMapping("/{id}")
        public TransactionResponseDTO updateTransaction(@PathVariable Long id,@RequestBody TransactionRequestDTO requestDTO) {

            return transactionService.updateTransaction(id, requestDTO);
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
            return transactionService.filterTransactions(type, minAmount, maxAmount, beforeDate, afterDate);
        }
}
