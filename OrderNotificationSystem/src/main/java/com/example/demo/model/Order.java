package com.example.demo.model;

import java.util.HashMap;

public abstract class Order {
    protected String id;
    protected String buyerName;
    protected OrderStatus status = OrderStatus.PLACED;

    Order(String id, String buyerName) {
        this.id = id;
        this.buyerName = buyerName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getId() {
        return id;
    }

    public abstract HashMap<String, Integer> getProducts();

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }


    public abstract void display();
}
