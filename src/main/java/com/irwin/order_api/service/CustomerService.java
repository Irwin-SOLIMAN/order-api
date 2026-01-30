package com.irwin.order_api.service;

import com.irwin.order_api.dto.CreateCustomerRequestDTO;
import com.irwin.order_api.entity.Customer;
import com.irwin.order_api.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(CreateCustomerRequestDTO newCustomer) {

        boolean isCustomerExist = customerRepository.findByEmail(newCustomer.email()).isPresent();

        if (isCustomerExist) {
            throw new IllegalArgumentException("Customer already exists with email: " + newCustomer.email());
        }

        Customer customer = new Customer(newCustomer.email(), newCustomer.firstName(), newCustomer.lastName());


        return customerRepository.save(customer);
    }

    public Customer getCustomerById(UUID customerID) {
        Customer customer = customerRepository.findById(customerID).orElseThrow(() -> new IllegalArgumentException("Customer with ID " + customerID + "not found"));
        return customer;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }



}
