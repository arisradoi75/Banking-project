package com.exemple.bankingproject.service;

import com.exemple.bankingproject.dto.TransactionRequestDTO;
import com.exemple.bankingproject.dto.TransactionResponseDTO;
import com.exemple.bankingproject.model.Category;
import com.exemple.bankingproject.model.Transaction;

import java.util.List;

public interface CategoryService {
   List<Category> getCategory();
   Category getCategoryById(Long id);
   Category saveCategory(Category category);
   Category updateCategory(Category category, Long id);
   void deleteCategory(Long id);



}
