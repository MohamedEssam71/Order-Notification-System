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
        for (int i = 0; i < products.size(); ++i) {
            System.out.print((i + 1) + "\n\tSerial: " + products.get(i));
        }
        System.out.println();
    }

    public void setProducts(HashMap<String, Integer> products) {
        this.products = products;
    }
}
