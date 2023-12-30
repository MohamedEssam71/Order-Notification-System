package com.example.demo.model;

import java.util.ArrayList;
import java.util.HashMap;

public class SimpleOrder extends Order {
    private HashMap<String, Integer> products;

    public SimpleOrder(String buyerName, String id, HashMap<String, Integer> products) {
        super(id, buyerName);
        this.products = products;
    }

    @Override
    public HashMap<String, Integer> getProducts() {
        return products;
    }

    @Override
    public void display() {
        System.out.print("ID: " + this.id + " Buyer: " + this.buyerName);
        int i = 1;
        for (String serial : products.keySet()) {
            System.out.print((i++) + "\n\tSerial: " + products.get(serial));
        }
        System.out.println();
    }

    public void setProducts(HashMap<String, Integer> products) {
        this.products = products;
    }
}
