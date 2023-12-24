package com.example.demo.service;

import com.example.demo.model.Order;

public interface OrderService {
    public Boolean addOrder(Order order);
    public Boolean removeOrder(String id);
    public Order getOrder(String id);
    public Double getOrderPrice(String id);
}
