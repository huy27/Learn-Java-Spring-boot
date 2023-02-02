package com.tutorial.apidemo.controller;

import com.tutorial.apidemo.database.ResponseObject;
import com.tutorial.apidemo.models.Product;
import com.tutorial.apidemo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/Products")
public class ProductController {
    //DI Dependency Injection
    @Autowired
    private ProductRepository repository;

    @GetMapping("")
    //Return status, message, data
    public ResponseEntity<ResponseObject> getAllProducts() {
        return ResponseEntity.ok(new ResponseObject("Ok", "Get All Product", repository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable(required = false) Integer id) {
        Optional<Product> product = repository.findById(id);
        return product.isPresent() ? ResponseEntity.ok(new ResponseObject("Ok", "Query Product success", product)) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("Not Found", "Cannot find product with id = " + id, ""));
    }

    //insert product
    @PostMapping("")
    public ResponseEntity<ResponseObject> insertProduct(@RequestBody Product product) {
        Boolean isExistProduct = repository.existsByProductName(product.getProductName());
        return isExistProduct ? ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseObject("Conflict", "Product already exist", "")) : ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Ok", "Insert Product success", repository.save(product)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        Optional<Product> optionalUpdateProduct = repository.findById(id);
        if (optionalUpdateProduct.isPresent()) {
            Product updateProduct = optionalUpdateProduct.get();
            updateProduct.setProductName(product.getProductName());
            updateProduct.setPrice(product.getPrice());
            updateProduct.setUrl(product.getUrl());
            return ResponseEntity.ok(new ResponseObject("Ok", "Update Product success", repository.save(updateProduct)));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("Not Found", "Product not found", ""));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable Integer id) {
        Optional<Product> optionalDeleteProduct = repository.findById(id);
        if (optionalDeleteProduct.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok(new ResponseObject("Ok", "Delete Product success", ""));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("Not Found", "Product not found", ""));
    }

}
