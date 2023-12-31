package com.example.demo.repository;

import com.example.demo.model.Order.Order;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.*;

@Repository
public class OrderRepository implements IRepository<String, Order> {
    HashMap<String, Order> orderDB = new HashMap<>();
    @Override
    public void put(String key, Order order) {
        orderDB.put(key, order);
    }

    @Override
    public Order get(String key) {
        return orderDB.get(key);
    }

    @Override
    public Boolean containsKey(String id) {
        return orderDB.containsKey(id);
    }

    @Override
    public ArrayList<Order> values() {
        return new ArrayList<>(orderDB.values());
    }
}
