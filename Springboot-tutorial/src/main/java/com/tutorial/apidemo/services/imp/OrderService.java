package com.tutorial.apidemo.services.imp;

import com.tutorial.apidemo.Entities.Order;
import com.tutorial.apidemo.dto.ResponseObject;
import com.tutorial.apidemo.repositories.CustomerRepository;
import com.tutorial.apidemo.repositories.OrderRepository;
import com.tutorial.apidemo.repositories.ProductRepository;
import com.tutorial.apidemo.repositories.VendorRepository;
import com.tutorial.apidemo.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public ResponseObject checkOut(Integer customerId, Integer productId, Integer quantity) {
        var customer = customerRepository.findById(customerId).orElse(null);
        if(!Optional.ofNullable(customer).isPresent())
            return new ResponseObject("400","Customer not found", null);

        var product = productRepository.findById(productId).orElse(null);
        if(!Optional.ofNullable(product).isPresent())
            return new ResponseObject("400","Product not found", null);

        var totalPrice = product.getPrice() * quantity;

        // Check if product is out of stock
        if(product.getQuantity() < quantity){
            throw new IllegalArgumentException("Product is out of stock");
        }

        // Customer don't have enough money
        if(customer.getCredit() < totalPrice){
            throw new IllegalArgumentException("Customer don't have enough money");
        }

        // Decrease product quantity when buy
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        //Subtract credit from customer when buy
        customer.setCredit(customer.getCredit() - totalPrice);
        customerRepository.save(customer);

        //Add credit to vendor
        var vendor = product.getVendor();
        vendor.setCredit(vendor.getCredit() + totalPrice);
        vendorRepository.save(vendor);

        //Save order
        var order = new Order();
        order.setCustomer(customer);
        order.setProduct(product);
        order.setQuantity(quantity);
        order.setTotalPrice(totalPrice);

        var result = orderRepository.save(order);
        return new ResponseObject("200", "Order successfully", result);
    }
}
