package com.codeGym.repositories;

import com.codeGym.models.Post;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post,Long> {
    List<Post> findAllByCategory_Id(Long category_id);
}
