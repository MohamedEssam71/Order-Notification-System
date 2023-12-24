package com.example.demo.model;

import java.util.ArrayList;

public abstract class Order {
    protected String id;
    Order(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract String getType();
    public abstract Double getPrice();
    public abstract ArrayList<Product> getProducts();
    public abstract void display();
}
