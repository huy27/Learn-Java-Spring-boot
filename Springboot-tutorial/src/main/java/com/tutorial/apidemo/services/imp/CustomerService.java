package com.tutorial.apidemo.services.imp;

import com.tutorial.apidemo.Entities.Customer;
import com.tutorial.apidemo.dto.request.CustomerRequest;
import com.tutorial.apidemo.dto.response.CustomerResponse;
import com.tutorial.apidemo.repositories.CustomerRepository;
import com.tutorial.apidemo.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<CustomerResponse> getAllCustomers() {
        var customers = customerRepository.findAll();
        var customerResponses = new ArrayList<CustomerResponse>();

        for (var customer : customers) {
            var customerResponse = new CustomerResponse();
            customerResponse.setId(customer.getId());
            customerResponse.setName(customer.getName());
            customerResponse.setCredit(customer.getCredit().toString());

            customerResponses.add(customerResponse);
        }
        return customerResponses;
    }

    @Override
    public boolean addCustomer(CustomerRequest customerRequest) {
        var customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setCredit(customerRequest.getCredit());
        customerRepository.save(customer);
        return true;
    }

    @Override
    public boolean updateCustomer(Integer id, CustomerRequest customerRequest) {
        var customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Could not find customer"));
        customer.setName(customerRequest.getName());
        customer.setCredit(customerRequest.getCredit());
        customerRepository.save(customer);
        return true;
    }
}
