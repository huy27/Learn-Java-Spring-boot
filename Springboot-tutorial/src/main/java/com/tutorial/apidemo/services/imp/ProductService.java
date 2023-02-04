package com.tutorial.apidemo.services.imp;

import com.tutorial.apidemo.Entities.Product;
import com.tutorial.apidemo.dto.request.ProductRequest;
import com.tutorial.apidemo.dto.response.ProductResponse;
import com.tutorial.apidemo.repositories.ProductRepository;
import com.tutorial.apidemo.repositories.VendorRepository;
import com.tutorial.apidemo.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private VendorRepository vendorRepository;
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<ProductResponse> getAllProducts() {
        var products = productRepository.findAll();
        var productResponses = new ArrayList<ProductResponse>();

        for (var product : products) {
            var productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setPrice(product.getPrice());
            productResponse.setQuantity(product.getQuantity());
            productResponse.setVendorId(product.getVendor().getId());
            productResponse.setVendorName(product.getVendor().getName());

            productResponses.add(productResponse);
        }
        return productResponses;
    }

    @Override
    public ProductResponse getProductById(int id) {
        return null;
    }

    @Override
    public boolean addProduct(ProductRequest productRequest) {
        var vendor = vendorRepository.findById(productRequest.getVendorId()).orElse(null);
        if (vendor == null) {
            return false;
        }

        var product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setVendor(vendor);

        productRepository.save(product);
        return true;
    }

    @Override
    public boolean updateProduct(Integer id, ProductRequest productRequest) {
        var vendor = vendorRepository.findById(productRequest.getVendorId()).orElse(null);
        if (vendor == null) {
            return false;
        }

        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return false;
        }

        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setVendor(vendor);

        productRepository.save(product);
        return true;
    }
}
