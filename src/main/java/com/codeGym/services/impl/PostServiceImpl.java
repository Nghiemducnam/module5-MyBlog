package com.codeGym.services.impl;

import com.codeGym.models.Category;
import com.codeGym.models.Exceptions.InvalidException;
import com.codeGym.models.Post;
import com.codeGym.repositories.CategoryRepository;
import com.codeGym.repositories.PostRepository;
import com.codeGym.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Post> findAllByCategory_CategoryId(Long category_id) {
        return postRepository.findAllByCategory_Id(category_id);
    }

    @Override
    public void deletePost(Long id) throws SQLException {
        Optional<Post> post = postRepository.findById(id);

        if (!post.isPresent()) {
            throw new InvalidException("THE POST IS NOT FOUND");
        }
        try {
            postRepository.deleteById(id);
        } catch (Exception e) {
            throw new SQLException("You can't delete this Post");
        }
    }

    @Override
    public void createPost(Post post) {
        try {
            postRepository.save(post);
        } catch (InvalidException e) {
            throw new InvalidException("there are some problems");
        }
    }

    @Override
    public void updatePost(Post post, Long id) {
        Optional<Post> currentPost = postRepository.findById(id);
        if (!currentPost.isPresent()) {
            throw new InvalidException("THE POST IS NOT FOUND");
        }
        currentPost.get().setTitle(post.getTitle());
        currentPost.get().setContent(post.getContent());
        currentPost.get().setImage(post.getImage());
        currentPost.get().setCategory(post.getCategory());
        postRepository.save(currentPost.get());
    }

    @Override
    public List<Post> getAllPost() {
        return (List<Post>) postRepository.findAll();
    }

    @Override
    public Optional<Post> findPostById(Long id) {
        Optional<Post> thisPost = postRepository.findById(id);
        if (!thisPost.isPresent()) {
            throw new InvalidException("THE POST IS NOT FOUND!");
        }
        return thisPost;
    }
    @Override
    public List<Post> getAllPostByCategoryId(Long category_id) {
        Optional<Category> categoryId = categoryRepository.findById(category_id);
        if (!categoryId.isPresent()) {
            throw new InvalidException("4.0.4 NOT FOUND");
        } else{
            List<Post> postList = postRepository.getAllPostByCategoryId(category_id);

            return postList;
        }
    }
}