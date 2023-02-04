package com.tutorial.apidemo.services;

import com.tutorial.apidemo.dto.ResponseObject;

public interface IOrderService {
    ResponseObject checkOut(Integer customerId, Integer productId, Integer quantity);
}
