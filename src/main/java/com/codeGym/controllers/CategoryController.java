package com.codeGym.controllers;

import com.codeGym.models.Category;
import com.codeGym.models.Exceptions.InvalidException;
import com.codeGym.models.Post;
import com.codeGym.models.ResponseMessage;
import com.codeGym.services.CategoryService;
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
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PostService postService;

    @PostMapping("/category")
    ResponseEntity<Category> createCategory(@RequestBody Category category, UriComponentsBuilder ucBuilder) {
        try {
            categoryService.saveCategory(category);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/api/admin/category/{id}").buildAndExpand(category.getCategoryId()).toUri());
            return new ResponseEntity<Category>(headers, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/category/{id}")
    ResponseEntity<ResponseMessage> getCategoryById(@PathVariable("id") Long id) {
        try {
            Optional<Category> thisCategory = categoryService.findByCategoryId(id);
            return new ResponseEntity<>(new ResponseMessage<>(true, "success", thisCategory), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage<>(false, e.getMessage(), null), HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/category")
    public ResponseEntity<ResponseMessage> getAllCategoryList(){
        try {
            List<Category> categoryList = categoryService.findAllCategory();
            return new ResponseEntity<ResponseMessage>(new ResponseMessage<>(
                    true, "Success", categoryList), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ResponseMessage>(new ResponseMessage(
                    false, "The system has some error",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/category/{id}")
    HttpEntity<ResponseMessage> deleteCategory(@PathVariable("id") Long id) {
        try {
            categoryService.deleteCategory(id);
            return new ResponseEntity<ResponseMessage>(new ResponseMessage(
                    true, "Remove category is succeed!", null), HttpStatus.NO_CONTENT);

        } catch (InvalidException e) {
            return new ResponseEntity<>(new ResponseMessage(
                    false, e.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (SQLException e) {
            return new ResponseEntity<>(new ResponseMessage(
                    false, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    //    @DeleteMapping("/category/{id}")
//    HttpEntity<?> deleteCategory(@PathVariable("id") Long id) {
//        Optional<Category> currentCategory = categoryService.findByCategoryId(id);
//
//        if(!currentCategory.isPresent()){
//            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
//        }else {
//            List<Post> postList = postService.findAllByCategory_CategoryId(id);
//            for(Post post: postList){
//                postService.deletePost(post);
//            }
//            categoryService.deleteCategory(id);
//            return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
//        }
//    }
}