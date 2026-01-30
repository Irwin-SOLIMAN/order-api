package com.irwin.order_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends  BaseEntity {

    @Column(nullable = false)
    private String name;

    private String description;

    @Positive
    private double price;

    private boolean active = true;

    @OneToMany(mappedBy="product", fetch = FetchType.LAZY)
    private List<OrderLine> orderLine = new ArrayList<>();


    public void setPrice(double newPrice) {
        if(price <= 0) throw new IllegalArgumentException("Price must be positive");
        this.price = newPrice;
    }


}
