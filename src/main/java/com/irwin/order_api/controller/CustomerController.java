package com.irwin.order_api.controller;

import com.irwin.order_api.dto.CreateCustomerRequestDTO;
import com.irwin.order_api.entity.Customer;
import com.irwin.order_api.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid CreateCustomerRequestDTO newCustomer) {
        Customer customerCreated = customerService.createCustomer(newCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerCreated);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable UUID id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomers());
    }


}
