package com.tutorial.apidemo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorRequest {
    private String name;
    private Double credit;
    private Boolean enabled;
}
