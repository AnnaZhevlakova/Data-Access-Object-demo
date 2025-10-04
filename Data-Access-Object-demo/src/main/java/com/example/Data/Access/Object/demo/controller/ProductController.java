package com.example.Data.Access.Object.demo.controller;

import com.example.Data.Access.Object.demo.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/products")
public class ProductController  {
private ProductRepository productRepository;

 public ProductController(ProductRepository productRepository){
     this.productRepository = productRepository;

 }

    @GetMapping("fetch-product")
    public ResponseEntity<?> getUser(String name) throws Exception {
        var result = productRepository.fetchProductByСlientName(name);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



}
