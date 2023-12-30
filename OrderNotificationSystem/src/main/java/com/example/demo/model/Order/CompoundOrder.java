package com.example.demo.model.Order;

import java.util.ArrayList;
import java.util.HashMap;

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
    public HashMap<String, Integer> getProducts() {
        HashMap<String, Integer> products = new HashMap<>();
        for (SimpleOrder order : orders) {
            HashMap<String, Integer> orderProducts = order.getProducts();
            for (String key : orderProducts.keySet()) {
                if (products.containsKey(key)) {
                    products.put(key, products.get(key) + orderProducts.get(key));
                } else {
                    products.put(key, orderProducts.get(key));
                }
            }
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
