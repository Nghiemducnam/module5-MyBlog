package com.codeGym.services.impl;

import com.codeGym.models.Category;
import com.codeGym.models.Exceptions.InvalidException;
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
    public void deleteCategory(Long id) throws SQLException {
        Optional<Category> category = categoryRepository.findById(id);

        if (!category.isPresent()) {
            throw new InvalidException("THE CATEGORY IS NOT FOUND");
        }
        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new SQLException("You can't delete this category");
        }
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