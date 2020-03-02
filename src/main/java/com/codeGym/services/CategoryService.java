package com.codeGym.services;

import com.codeGym.models.Category;
import com.codeGym.models.Post;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void saveCategory(Category category);
    void deleteCategory(Long id);
    List<Category>findAllCategory();
    Optional<Category> findByCategoryId(Long id);
   

}
