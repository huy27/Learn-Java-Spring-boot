package com.tutorial.apidemo.controller;

import com.tutorial.apidemo.dto.ResponseObject;
import com.tutorial.apidemo.dto.request.CustomerRequest;
import com.tutorial.apidemo.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @GetMapping()
    public ResponseEntity<ResponseObject> getAllCustomers() {
        var customers = customerService.getAllCustomers();
        return ResponseEntity.ok(new ResponseObject("200", "", customers));
    }

    @PostMapping
    public ResponseEntity<ResponseObject> createCustomer(@RequestBody CustomerRequest customerRequest) {
        var isSuccess = customerService.addCustomer(customerRequest);
        return isSuccess
            ? ResponseEntity.ok(new ResponseObject("200", "Create customer success", customerRequest))
            : ResponseEntity.badRequest().body(new ResponseObject("400", "Create customer failed", ""));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateCustomer(@PathVariable("id") Integer id, @RequestBody CustomerRequest customerRequest) {
        var isSuccess = customerService.updateCustomer(id, customerRequest);
        return isSuccess
                ? ResponseEntity.ok(new ResponseObject("200", "Update customer success", customerRequest))
                : ResponseEntity.badRequest().body(new ResponseObject("400", "Update customer failed", ""));
    }
}
