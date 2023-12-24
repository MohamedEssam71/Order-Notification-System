package com.example.demo.model;

import com.example.demo.service.OrderService;

import java.util.ArrayList;

public class CompoundOrder extends Order{
    private ArrayList<Order> orders;
    String type;
    public CompoundOrder(String id, String type,ArrayList<Order>orders) {
        super(id);
        this.type = type;
        this.orders = orders;
    }
//    public CompoundOrder(Order order, OrderService orderService) {
//        super(order.getId());
//        for (Order childOrder : order.getChildOrders()) {
//            Order newChildOrder = orderService.addOrder(childOrder);
//            this.addOrder(newChildOrder);
//        }
//    }
    @Override
    public String getType() {
        return "compound";
    }

    public void addOrder(Order order){
        orders.add(order);
    }
    public ArrayList<Order> getOrders() {
        return orders;
    }

    @Override
    public Double getPrice() {
        return orders.stream().mapToDouble(Order::getPrice).sum();
    }

    @Override
    public ArrayList<Product> getProducts(){
        return null;
    }

    @Override
    public void display() {
        System.out.println("ID: " + this.id + " Type: " + this.type);
        for(int i = 0; i < orders.size(); ++i){
            orders.get(i).display();
            System.out.println("------------------------------------");
        }
    }
}
