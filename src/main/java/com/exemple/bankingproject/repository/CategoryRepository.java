package com.exemple.bankingproject.repository;

import com.exemple.bankingproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Long id(Long id);
    boolean existsByCategoryName(String categoryName);
    boolean getCategoryById(Long id);
}
