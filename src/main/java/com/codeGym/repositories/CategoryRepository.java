package com.codeGym.repositories;

import com.codeGym.models.Category;
import com.codeGym.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
    @Query(value = "select c.category_id, c.category_name from category c where category_id = ?1", nativeQuery = true)
    Optional<Category> getCategoryById(Long category_id);


}
