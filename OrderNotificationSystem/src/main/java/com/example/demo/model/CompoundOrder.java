package com.example.demo.model;

import com.example.demo.service.OrderService;

import java.util.ArrayList;

public class CompoundOrder extends Order{
    private ArrayList<SimpleOrder> orders;
    String type;
    public CompoundOrder(String id, String type, ArrayList<SimpleOrder>orders) {
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

    public void addOrder(SimpleOrder order){
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
    public ArrayList<Product> getProducts(){
        ArrayList<Product> products = new ArrayList<>();
        for(int i = 0; i < orders.size(); ++i){
            products.addAll(orders.get(i).getProducts());
        }
        return products;
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
