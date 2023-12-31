package com.example.demo.repository;

import com.example.demo.model.Order.OrderType;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.stereotype.*;

@Repository
public class OrderTypeRepository implements IRepository<String, OrderType> {
    HashMap<String, OrderType> orderTypeDB = new HashMap<>();
    @Override
    public void put(String key, OrderType orderType) {
        orderTypeDB.put(key, orderType);
    }

    @Override
    public OrderType get(String key) {
        return orderTypeDB.get(key);
    }

    @Override
    public Boolean containsKey(String id) {
        return orderTypeDB.containsKey(id);
    }

    @Override
    public ArrayList<OrderType> values() {
        return new ArrayList<>(orderTypeDB.values());
    }
}
