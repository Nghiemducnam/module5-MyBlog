package com.codeGym.services;

import com.codeGym.models.Post;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> findAllByCategory_CategoryId(Long category_id);
    void deletePost(Long id) throws SQLException;
    void createPost(Post post);
    void updatePost(Post post, Long id);
    List<Post> getAllPost();
    Optional<Post> findPostById(Long id);
    List<Post> getAllPostByCategoryId(Long category_id);
}
