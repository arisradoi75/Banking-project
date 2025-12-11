package com.exemple.bankingproject.service;

import com.exemple.bankingproject.model.Transaction;
import com.exemple.bankingproject.model.TransactionType;
import com.exemple.bankingproject.repository.TransactionRepository;
import com.exemple.bankingproject.specifications.TransactionSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactions(){
        return transactionRepository.findAll();
    }

    public Transaction saveTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public void deleteTransactionById(Long id){
        transactionRepository.deleteById(id);
    }



    public Transaction updateTransaction(Long id, Transaction transaction){
        return transactionRepository.findById(id)
                .map(existing -> transactionRepository.save(transaction))
                .orElseThrow( () -> new RuntimeException("Transaction not found!"));
    }



    public List<Transaction> getByType(TransactionType transactionType){
        return transactionRepository.findByType(transactionType);
    }

    public List<Transaction> filterTransactions(TransactionType type,
                                                BigDecimal minAmount,
                                                BigDecimal maxAmount,
                                                LocalDateTime beforeDate,
                                                LocalDateTime afterDate
    ) {
        Specification<Transaction> specifications = Specification
                .where(TransactionSpecifications.hasType(type)
                .and(TransactionSpecifications.minAmount(minAmount))
                .and(TransactionSpecifications.maxAmount(maxAmount))
                        .and(TransactionSpecifications.BeforeDate(beforeDate))
                        .and(TransactionSpecifications.AfterDate(afterDate)));

        return transactionRepository.findAll(specifications);

    }





}
