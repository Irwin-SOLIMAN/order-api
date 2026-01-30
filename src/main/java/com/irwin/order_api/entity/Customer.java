package com.irwin.order_api.entity;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String email;

    private String firstName;

    private String lastName;

    @OneToMany (mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}


