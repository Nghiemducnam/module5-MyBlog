package com.codeGym.services;

import com.codeGym.models.Post;

import java.util.List;

public interface PostService {
    List<Post> findAllByCategory_CategoryId(Long category_id);
    void deletePost(Post post);
}
