package com.codeGym.services.impl;

import com.codeGym.models.Post;
import com.codeGym.repositories.PostRepository;
import com.codeGym.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Override
    public List<Post> findAllByCategory_CategoryId(Long category_id) {
        return postRepository.findAllByCategory_CategoryId(category_id);
    }

    @Override
    public void deletePost(Post post) {
        postRepository.delete(post);
    }
}