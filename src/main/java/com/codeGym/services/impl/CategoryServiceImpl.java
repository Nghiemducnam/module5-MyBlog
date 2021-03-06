package com.codeGym.services.impl;

import com.codeGym.models.Category;
import com.codeGym.models.Exceptions.InvalidException;
import com.codeGym.models.Post;
import com.codeGym.repositories.CategoryRepository;
import com.codeGym.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void createCategory(Category category) {
        try {
            categoryRepository.save(category);
        } catch (InvalidException e) {
            throw new InvalidException("there are some problems");
        }
    }

    @Override
    public void editCategory(Category category, Long id) {
        Optional<Category> currentCategory = categoryRepository.findById(id);
        if (!currentCategory.isPresent()) {
            throw new InvalidException("THE CATEGORY IS NOT FOUND");
        }
//        currentCategory.get().setId(category.getId());
        currentCategory.get().setCategoryName(category.getCategoryName());
        categoryRepository.save(currentCategory.get());
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
        Optional<Category> thisCategory = categoryRepository.getCategoryById(id);
        if (!thisCategory.isPresent()) {
            throw new InvalidException("THE CATEGORY NOT FOUND!");
        }
        return thisCategory;
    }


}