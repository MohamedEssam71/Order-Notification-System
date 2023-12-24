package com.example.demo.service;

import com.example.demo.Database;
import com.example.demo.model.Order;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;

@Service
public class OrderServiceImp implements OrderService{

    @Override
    public Boolean addOrder(Order order) {
        if(Database.orderDB.get(order.getId()) != null){
            return false;
        }
        Database.orderDB.put(order.getId(), order);
        return true;
    }

    @Override
    public Boolean removeOrder(String id) {
        return Database.orderDB.remove(id) != null;
    }

    @Override
    public Order getOrder(String id) {
        return Database.orderDB.get(id);
    }

    @Override
    public Double getOrderPrice(String id) {
        return Database.orderDB.get(id).getPrice();
    }
}
