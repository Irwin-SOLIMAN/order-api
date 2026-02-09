package com.irwin.order_api.service;

import com.irwin.order_api.dto.CreateOrderLineRequestDTO;
import com.irwin.order_api.dto.CreateOrderRequestDTO;
import com.irwin.order_api.entity.Customer;
import com.irwin.order_api.entity.Order;
import com.irwin.order_api.entity.Product;
import com.irwin.order_api.repository.CustomerRepository;
import com.irwin.order_api.repository.OrderRepository;
import com.irwin.order_api.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;


    public OrderService (OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository ) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }


    public Order createOrder(CreateOrderRequestDTO orderRequestDTO) {

        Customer customer = customerRepository.findById(orderRequestDTO.customerId()).orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setReference(generateReference());


        for(CreateOrderLineRequestDTO lineRequest : orderRequestDTO.lines() ) {


            Product product = productRepository.findById(lineRequest.productId()).orElseThrow(() -> new IllegalArgumentException("Product not found"));

            if(!product.isActive()) {
                throw new IllegalArgumentException("This product is not active");
            }

            order.addOrderLine(product, lineRequest.quantity());


        }
        order.setTotalAmount(order.calculateTotalAmount());
        orderRepository.save(order);


        return order;
    }


    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(UUID id) {
        return orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order with ID " + id + " not found"));
    }

    private String generateReference() {
        return "ORD-" + System.currentTimeMillis();
    }

}


