package com.exemple.bankingproject.repository;

import com.exemple.bankingproject.model.Transaction;
import com.exemple.bankingproject.model.TransactionType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {
    List<Transaction> findByType(TransactionType transactionType);

    Long id(Long id);
}
