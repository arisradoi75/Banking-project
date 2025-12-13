package com.exemple.bankingproject.dto;

import com.exemple.bankingproject.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponseDTO {
    private BigDecimal amount;
    private LocalDateTime dateTime;
    private TransactionType transactionType;
    private String notes;

    private Long categoryId;
    private String categoryName;
    public TransactionResponseDTO() {

    }

    public TransactionResponseDTO(BigDecimal amount, LocalDateTime dateTime, TransactionType transactionType, String notes, Long categoryId, String categoryName) {
        this.amount = amount;
        this.dateTime = dateTime;
        this.transactionType = transactionType;
        this.notes = notes;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
