package com.tutorial.apidemo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Integer id;
    private String name;
    private Double price;
    private Integer quantity;
    private Integer vendorId;
    private String vendorName;
}
