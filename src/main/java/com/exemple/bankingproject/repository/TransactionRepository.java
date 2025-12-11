package com.exemple.bankingproject.repository;

import com.exemple.bankingproject.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
