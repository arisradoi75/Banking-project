package com.exemple.bankingproject.config;

import com.exemple.bankingproject.model.Category;
import com.exemple.bankingproject.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryInitializer implements CommandLineRunner {
    private final CategoryRepository categoryRepository;

    public CategoryInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<String> predefinedCategs = List.of("FOOD", "TRANSPORT", "RENT", "SALARY", "ENTERTAINMENT", "UTILITIES");

    @Override
    public void run(String... args) throws Exception {
        for (String name : predefinedCategs) {
            if(!categoryRepository.existsByCategoryName(name)){
                Category category =  new Category();
                category.setCategoryName(name);
                categoryRepository.save(category);
            }
        }
    }
}
