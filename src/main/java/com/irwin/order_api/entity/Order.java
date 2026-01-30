package com.irwin.order_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
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


    public void addOrderLine(OrderLine lineToAdd) {
        this.checkOrderStatus();
        lineToAdd.setOrder(this);
        this.orderLines.add(lineToAdd);
    }

    public void removeOrderLine(OrderLine lineToRemove) {
        this.checkOrderStatus();
        lineToRemove.setOrder(this);
        this.orderLines.remove(lineToRemove);
    }

    public double calculateTotalAount() {
        return orderLines.stream().mapToDouble(orderLine -> orderLine.calculateLineAmount()).sum();
    }


    private void checkOrderStatus() {
        if(this.status == OrderStatus.PAID) {
            throw new IllegalArgumentException("This command is already paid and therefore not be modified");
        }
    }





}
