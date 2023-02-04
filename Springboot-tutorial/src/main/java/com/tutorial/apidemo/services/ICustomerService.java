package com.tutorial.apidemo.services;

import com.tutorial.apidemo.Entities.Customer;
import com.tutorial.apidemo.dto.request.CustomerRequest;
import com.tutorial.apidemo.dto.response.CustomerResponse;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ICustomerService {
    List<CustomerResponse> getAllCustomers();
    boolean addCustomer(CustomerRequest customerRequest);
    boolean updateCustomer(Integer id, CustomerRequest customerRequest);
}
