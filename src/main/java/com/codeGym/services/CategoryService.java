package com.codeGym.services;

import com.codeGym.models.Category;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void saveCategory(Category category);
    void deleteCategory(Long id) throws SQLException;
    List<Category>findAllCategory();
    Optional<Category> findByCategoryId(Long id);
   

}
