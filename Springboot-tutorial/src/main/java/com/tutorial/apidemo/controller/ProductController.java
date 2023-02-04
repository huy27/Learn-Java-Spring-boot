package com.tutorial.apidemo.controller;

import com.tutorial.apidemo.dto.ResponseObject;
import com.tutorial.apidemo.dto.request.ProductRequest;
import com.tutorial.apidemo.services.IProductService;
import com.tutorial.apidemo.services.imp.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/Products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping()
    public ResponseEntity<ResponseObject> getAllProducts() {
        return ResponseEntity.ok(new ResponseObject("200", "", productService.getAllProducts()));
    }

    @PostMapping()
    public ResponseEntity<ResponseObject> addProduct(@RequestBody ProductRequest productRequest) {
        var isSuccess = productService.addProduct(productRequest);
        return isSuccess
                ? ResponseEntity.ok(new ResponseObject("200", "Add Product Success", productRequest))
                : ResponseEntity.badRequest().body(new ResponseObject("400", "Add Product Fail", ""));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ResponseObject> updateProduct(@PathVariable Integer id, @RequestBody ProductRequest productRequest) {
        var isSuccess = productService.updateProduct(id, productRequest);
        return isSuccess
                ? ResponseEntity.ok(new ResponseObject("200", "Update Product Success", productRequest))
                : ResponseEntity.badRequest().body(new ResponseObject("400", "Update Product Fail", ""));
    }
}
