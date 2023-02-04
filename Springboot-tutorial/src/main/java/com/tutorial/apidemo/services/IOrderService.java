package com.tutorial.apidemo.services;

import com.tutorial.apidemo.dto.ResponseObject;
import com.tutorial.apidemo.dto.response.OrderResponse;

public interface IOrderService {
    ResponseObject checkOut(Integer customerId, Integer productId, Integer quantity);

    OrderResponse getOrders(Integer customerId);
}
