package com.example.demo.model;

import com.example.demo.model.Account.Account;

import java.util.ArrayList;

public abstract class Order {
    protected String id;
    protected String buyerName;

    protected Boolean shipped = false;

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

    public abstract Double getPrice();

    public abstract ArrayList<Product> getProducts();

    public Boolean getShipped() {
        return shipped;
    }

    public void setShipped(Boolean shipped) {
        this.shipped = shipped;
    }


    public abstract void display();
}
