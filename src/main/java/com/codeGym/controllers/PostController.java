package com.codeGym.controllers;

import com.codeGym.models.Exceptions.InvalidException;
import com.codeGym.models.Post;
import com.codeGym.models.ResponseMessage;
import com.codeGym.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/post")
    ResponseEntity<?> createPost(@RequestBody Post post, UriComponentsBuilder ucBuilder) {
        try {
            postService.createPost(post);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/api/admin/post/{id}").buildAndExpand(post.getPostId()).toUri());
            return new ResponseEntity<Post>(headers, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage<>(false, "lỗi", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/post/{id}")
    ResponseEntity<ResponseMessage> getCategoryById(@PathVariable("id") Long id) {
        try {
            Optional<Post> thisPost = postService.findPostById(id);
            return new ResponseEntity<>(new ResponseMessage<>(true, "success", thisPost), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage<>(false, e.getMessage(), null), HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/post")
    public ResponseEntity<ResponseMessage> getAllCategoryList() {
        try {
            List<Post> postList = postService.getAllPost();
            return new ResponseEntity<ResponseMessage>(new ResponseMessage<>(
                    true, "Success", postList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ResponseMessage>(new ResponseMessage(
                    false, "The system has some error", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/post/{id}")
    HttpEntity<ResponseMessage> deleteCategory(@PathVariable("id") Long id) {
        try {
            postService.deletePost(id);
            return new ResponseEntity<ResponseMessage>(new ResponseMessage(
                    true, "Remove post is succeed!", null), HttpStatus.NO_CONTENT);

        } catch (InvalidException e) {
            return new ResponseEntity<>(new ResponseMessage(
                    false, e.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (SQLException e) {
            return new ResponseEntity<>(new ResponseMessage(
                    false, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping("/post/{id}")
    HttpEntity<ResponseMessage> updateCategory(@PathVariable("id") Long id, @RequestBody Post post) {
        try {
            postService.updatePost(post, id);
            return new ResponseEntity<>(new ResponseMessage(
                    true, "Okay", post), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(
                    false, e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/category/post/{id}")
    ResponseEntity<ResponseMessage> findAllPostByCategory(@PathVariable("id") Long id){
        try{
            List<Post> postList = postService.getAllPostByCategoryId(id);
            return new ResponseEntity<>(new ResponseMessage(
                    true, "success!", postList), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(
                    false, e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }
}