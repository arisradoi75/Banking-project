package com.exemple.bankingproject.service;

import com.exemple.bankingproject.model.Category;
import com.exemple.bankingproject.repository.CategoryRepository;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategory(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id){
        return categoryRepository
                .findById(id)
                .orElseThrow( () -> new RuntimeException("Category with id " + id + " not found!") );
    }

    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category, Long id){
        return categoryRepository.findById(id)
                .map(existing -> categoryRepository.save(category))
                .orElseThrow( () -> new RuntimeException("Category with id " + id + " not found!") );
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }


}
