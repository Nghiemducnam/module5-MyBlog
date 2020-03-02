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
//    @Query(value = "select post_id from post inner join category on post.category_id = category.category_id, where category_id = ?1", nativeQuery = true)
//    List<Post> findAllPostByCategoryId(Long category_id);

}
