package com.codeGym.services.impl;

import com.codeGym.models.Category;
import com.codeGym.repositories.CategoryRepository;
import com.codeGym.services.CategoryService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;
    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);

    }

    @Override
    public List<Category> findAllCategory() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findByCategoryId(Long id) {
        return categoryRepository.findById(id);
    }
}