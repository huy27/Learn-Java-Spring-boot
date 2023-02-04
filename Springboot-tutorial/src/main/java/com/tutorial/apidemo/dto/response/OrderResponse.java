package com.tutorial.apidemo.dto.response;

import com.tutorial.apidemo.Entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Integer customerId;
    private String customerName;
    private List<Product> products;
    private Double totalPrice;
}

