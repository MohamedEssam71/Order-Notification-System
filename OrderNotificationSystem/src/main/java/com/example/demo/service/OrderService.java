package com.example.demo.service;

import com.example.demo.Database;
import com.example.demo.model.Account.Account;
import com.example.demo.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class OrderService implements IOrderService {
    @Autowired
    AccService accService;
    @Autowired
    IProductService productService;

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
        return buyer.getBalance() >= calculateOrderPrice(order);
    }

    @Override
    public Boolean validateOrderItemsAvailable(Order order) {
        HashMap<String, Integer> products = order.getProducts();
        for (String serial : products.keySet()) {
            Integer quantity = products.get(serial);
            if (!productService.productExists(serial)) {
                return false;
            }
            if (!productService.checkAvailable(serial, quantity)) {
                return false;
            }
        }
        return true;
    }


    @Override
    public Boolean payOrder(Order order, double fees) {
        for (String serial : order.getProducts().keySet()) {
            Integer quantity = order.getProducts().get(serial);
            productService.updateQuantity(serial, -quantity);
        }
        return accService.updateBalance(order.getBuyerName(), -(calculateOrderPrice(order) + fees));
    }

    @Override
    public Boolean refundOrder(Order order, double fees) {
        for (String serial : order.getProducts().keySet()) {
            Integer quantity = order.getProducts().get(serial);
            productService.updateQuantity(serial, quantity);
        }
        Double returnedFees = order.getShipped() ? 0 : fees * 0.5;
        return accService.updateBalance(order.getBuyerName(), calculateOrderPrice(order) + returnedFees);
    }

    @Override
    public Boolean shipOrder(String id) {
        Order order = getOrder(id);
        order.setShipped(true);
        return true;
    }

    public Double calculateOrderPrice(Order order) {
        Double total = 0.0;
        for (String serial : order.getProducts().keySet()) {
            Integer quantity = order.getProducts().get(serial);
            Double price = productService.get(serial).getPrice() * quantity;
            total += price;
        }
        return total;
    }


}
