package com.exemple.bankingproject.specifications;

import com.exemple.bankingproject.model.Transaction;
import com.exemple.bankingproject.model.TransactionType;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;

public class TransactionSpecifications {

    public static Specification<Transaction> hasType(TransactionType transactionType) {
        return (root, query, cb) ->
                transactionType == null ? null : cb.equal(root.get("transactionType"), transactionType);
    }

    public static Specification<Transaction> minAmount(BigDecimal amount) {
        return(root, query, cb) ->
                amount == null ? null : cb.greaterThanOrEqualTo(root.get("amount"), amount);
    }

    public static Specification<Transaction> maxAmount(BigDecimal amount) {
        return(root, query, cb) ->
                amount == null ? null : cb.lessThanOrEqualTo(root.get("amount"), amount);
    }

    public static Specification<Transaction> BeforeDate(LocalDateTime beforeDate) {
        return(root, query, cb) ->
                beforeDate == null ? null : cb.lessThan(root.get("dateTime"), beforeDate);
    }

    public static Specification<Transaction> AfterDate(LocalDateTime afterDate) {
        return(root, query, cb) ->
                afterDate == null ? null : cb.greaterThan(root.get("dateTime"), afterDate);
    }


}
