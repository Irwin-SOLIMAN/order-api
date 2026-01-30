package com.irwin.order_api.entity;

import jakarta.persistence.*;

@Entity
public class OrderLine {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private double quantity;

     OrderLine(Order order, Product product, double quantity) {
        this.product = product;
        this.quantity = quantity;
         this.order = order;
    }


    double calculateLineAmount() {
        return this.product.getPrice() * quantity;
    }
}
