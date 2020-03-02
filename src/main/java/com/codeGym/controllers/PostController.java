package com.codeGym.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PostController {
//    @DeleteMapping("/admin/product/{id}")
//    public ResponseEntity<?> deletePost(@PathVariable("id") Long id){
//        Optional<Product> product = productService.findById(id);
//        if (!product.isPresent()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        productService.delete(id);
//        return new ResponseEntity<>(product, HttpStatus.OK);
//    }
}