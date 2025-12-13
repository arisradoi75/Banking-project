package com.exemple.bankingproject.service;

import com.exemple.bankingproject.dto.TransactionRequestDTO;
import com.exemple.bankingproject.dto.TransactionResponseDTO;
import com.exemple.bankingproject.model.Category;
import com.exemple.bankingproject.model.Transaction;
import com.exemple.bankingproject.model.TransactionType;
import com.exemple.bankingproject.repository.CategoryRepository;
import com.exemple.bankingproject.repository.TransactionRepository;
import com.exemple.bankingproject.specifications.TransactionSpecifications;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, CategoryRepository categoryRepository){
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<TransactionResponseDTO> getTransactions(){

        return transactionRepository.findAll()
                .stream()
                .map(this::maptoDTO)
                .toList();
    }

    public TransactionResponseDTO saveTransaction(TransactionRequestDTO requestDTO){
            Transaction transaction = maptoEntity(requestDTO);
            Transaction savedTransaction = transactionRepository.save(transaction);
            return maptoDTO(savedTransaction);
    }

    public void deleteTransactionById(Long id){
        transactionRepository.deleteById(id);
    }



    public TransactionResponseDTO updateTransaction(Long id, TransactionRequestDTO requestDTO){
        Category category = categoryRepository.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

            Transaction existingTransaction = transactionRepository
                    .findById(id)
                    .orElseThrow(()-> new EntityNotFoundException("Transaction not found"));

            existingTransaction.setAmount(requestDTO.getAmount());
            existingTransaction.setCategory(category);
            existingTransaction.setType(requestDTO.getTransactionType());
            existingTransaction.setNotes(requestDTO.getNotes());
            existingTransaction.setDateTime(requestDTO.getDateTime());

            Transaction savedTransaction = transactionRepository.save(existingTransaction);
            return maptoDTO(savedTransaction);
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

    public Transaction maptoEntity(TransactionRequestDTO dto){
            Category category = categoryRepository
                    .findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category with id " + dto.getCategoryId() + " not found!"));

            Transaction transaction = new Transaction();

                transaction.setType(dto.getTransactionType());
                transaction.setAmount(dto.getAmount());
                transaction.setNotes(dto.getNotes());
                transaction.setDateTime(dto.getDateTime());
                transaction.setCategory(category);

            return transaction;
    }

    public TransactionResponseDTO maptoDTO(Transaction entity){
        TransactionResponseDTO dto = new TransactionResponseDTO();

        dto.setAmount(entity.getAmount());
        dto.setDateTime(entity.getDateTime());
        dto.setTransactionType(entity.getType());
        dto.setNotes(entity.getNotes());
        dto.setCategoryId(entity.getCategory().getId());
        dto.setCategoryName(entity.getCategory().getCategoryName());

        return dto;
    }

}
