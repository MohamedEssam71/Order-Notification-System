package com.example.demo.model;

import java.util.ArrayList;

public class CompoundOrder extends Order {
    private ArrayList<SimpleOrder> orders;

    public CompoundOrder(String buyerName, String id, ArrayList<SimpleOrder> orders) {
        super(id, buyerName);
        this.orders = orders;
    }

    public void addOrder(SimpleOrder order) {
        orders.add(order);
    }

    public ArrayList<SimpleOrder> getOrders() {
        return orders;
    }

    @Override
    public Double getPrice() {
        return orders.stream().mapToDouble(Order::getPrice).sum();
    }

    @Override
    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < orders.size(); ++i) {
            products.addAll(orders.get(i).getProducts());
        }
        return products;
    }

    @Override
    public void display() {
        System.out.println("ID: " + this.id + " Buyer: " + this.buyerName);
        for (int i = 0; i < orders.size(); ++i) {
            orders.get(i).display();
            System.out.println("------------------------------------");
        }
    }
}
