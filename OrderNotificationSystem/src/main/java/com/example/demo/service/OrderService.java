package com.example.demo.service;

import com.example.demo.Database;
import com.example.demo.model.Account.Account;
import com.example.demo.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {
    @Autowired
    AccService accService;

    @Override
    public Integer addOrder(Order order) {
        if (Database.orderDB.containsKey(order.getId())) {
            return 0;
        }

        String buyer = order.getBuyerName();
        if (!accService.userExists(buyer)) {
            return -1;
        }

        Database.orderDB.put(order.getId(), order);
        return 1;
    }

    @Override
    public Boolean removeOrder(String id) {
        return Database.orderDB.remove(id) != null;
    }

    @Override
    public Order getOrder(String id) {
        Order order = Database.orderDB.get(id);
        order.display();
        return order;
    }

    @Override
    public Boolean validateOrder(Order order) {
        Account buyer = accService.getUser(order.getBuyerName());
        if (buyer == null) {
            return false;
        }
        return buyer.getBalance() >= order.getPrice();
    }

    @Override
    public Boolean payOrder(Order order, double fees) {
        return accService.updateBalance(order.getBuyerName(), -(order.getPrice() + fees));
    }


    @Override
    public Double getOrderPrice(String id) {
        Order order = Database.orderDB.get(id);
        return order.getPrice();
    }


}
