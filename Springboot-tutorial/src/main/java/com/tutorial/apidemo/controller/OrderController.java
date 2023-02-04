package com.tutorial.apidemo.controller;

import com.tutorial.apidemo.dto.ResponseObject;
import com.tutorial.apidemo.dto.request.OrderRequest;
import com.tutorial.apidemo.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @PostMapping
    @Transactional
    public ResponseEntity<ResponseObject> checkout(@RequestBody OrderRequest orderRequest) {
        var responseObject = orderService.checkOut(orderRequest.getCustomerId(), orderRequest.getProductId(), orderRequest.getQuantity());
        return responseObject.getStatus().equals("200")
                ? ResponseEntity.ok(responseObject)
                : ResponseEntity.badRequest().body(responseObject);
    }
}
