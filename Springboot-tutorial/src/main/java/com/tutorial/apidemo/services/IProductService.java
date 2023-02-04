package com.tutorial.apidemo.services;

import com.tutorial.apidemo.dto.request.ProductRequest;
import com.tutorial.apidemo.dto.response.ProductResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IProductService {
    @Transactional(readOnly = true)
    List<ProductResponse> getAllProducts();
    @Transactional(readOnly = true)
    ProductResponse getProductById(int id);
    boolean addProduct(ProductRequest productRequest);
    boolean updateProduct(Integer id, ProductRequest productRequest);
}
