package com.irwin.order_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customer_order")
public class Order extends BaseEntity {

    private String reference;

    private OrderStatus status = OrderStatus.CREATED;

    private double totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    @NotNull
    private List<OrderLine> orderLines = new ArrayList<>();


    public void addOrderLine(Product product, double quantity) {
        checkOrderStatus();

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        OrderLine line = new OrderLine(this, product, quantity );

        this.orderLines.add(line);
    }


    public void removeOrderLine(OrderLine lineToRemove) {
        checkOrderStatus();

        if (!this.orderLines.contains(lineToRemove)) {
            throw new IllegalArgumentException("OrderLine does not belong to this Order");
        }

        this.orderLines.remove(lineToRemove);

    }

    public double calculateTotalAmount() {
        return orderLines.stream().mapToDouble(orderLine -> orderLine.calculateLineAmount()).sum();
    }


    private void checkOrderStatus() {
        if(this.status == OrderStatus.PAID) {
            throw new IllegalArgumentException("This command is already paid and therefore not be modified");
        }
    }





}
