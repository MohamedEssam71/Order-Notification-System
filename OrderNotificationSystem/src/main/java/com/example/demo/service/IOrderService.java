package com.example.demo.service;

import com.example.demo.model.Order;

public interface IOrderService {
    public Integer addOrder(Order order);
    public Boolean removeOrder(String id);
    public Order getOrder(String id);
    public Boolean validateOrder(Order order);
    public Boolean validateOrderItemsAvailable(Order order);
    public Boolean payOrder(Order order, double fees);
    public Boolean refundOrder(Order order, double fees);
    public Boolean shipOrder(String id);
    public Boolean orderExists(String id);
    public Double calculateOrderPrice(Order order);
}
