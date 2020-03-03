package com.codeGym.services;

import com.codeGym.models.Category;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void createCategory(Category category);
    void editCategory(Category category, Long id);
    void deleteCategory(Long id) throws SQLException;
    List<Category>findAllCategory();
    Optional<Category> findByCategoryId(Long id);

   

}
