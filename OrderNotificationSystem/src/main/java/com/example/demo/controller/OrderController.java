package com.example.demo.controller;

import com.example.demo.Database;
import com.example.demo.model.*;
import com.example.demo.service.OrderServiceImp;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderServiceImp orderServiceImp;
    @PostMapping("/add")
    public Response addOrder(@RequestBody JsonNode order){
        Response response = new Response();
        Order newOrder;
        if (order.get("type").textValue().equals("simple")) {
            newOrder = createSimpleOrder(order);
        } else if (order.get("type").textValue().equals("compound")) {
            newOrder = createCompoundOrder(order);
        } else {
            response.setStatus(false);
            response.setMessage("Invalid order type");
            return response;
        }

        newOrder.display();
        Database.orderDB.put(newOrder.getId(), newOrder);
        response.setStatus(true);
        response.setMessage("Order added successfully");
        return response;
    }
    private Order createSimpleOrder(JsonNode order) {
        ArrayList<Product> products = new ArrayList<>();

        for (JsonNode productNode : order.get("products")) {
            Product product = createProduct(productNode);
            products.add(product);
        }

        return new SimpleOrder(order.get("id").textValue(), "simple", products);
    }

    private Order createCompoundOrder(JsonNode order) {
        ArrayList<Order> subOrders = new ArrayList<>();

        for (JsonNode subOrderNode : order.get("subOrders")) {
            Order subOrder;
            if (subOrderNode.get("type").textValue().equals("simple")) {
                subOrder = createSimpleOrder(subOrderNode);
            } else if (subOrderNode.get("type").textValue().equals("compound")) {
                subOrder = createCompoundOrder(subOrderNode);
            } else {
                // Handle unknown sub-order type
                throw new IllegalArgumentException("Unknown sub-order type");
            }

            subOrders.add(subOrder);
        }

        return new CompoundOrder(order.get("id").textValue(), "compound", subOrders);
    }

    private Product createProduct(JsonNode productNode) {
        String serial = productNode.get("id").textValue();
        String name = productNode.get("name").textValue();
        Double price = productNode.get("price").doubleValue();
        Integer available = productNode.get("available").intValue();

        return new Product(serial, name, price, available);
    }

    @DeleteMapping("/delete/{id}")
    public Response removeOrder(@PathVariable("id") String id){
        System.out.println("in delete with id:"+id);
        boolean res = orderServiceImp.removeOrder(id);
        Response response = new Response();
        if (!res) {
            response.setStatus(false);
            response.setMessage("Order Doesn't Exists");
            return response;
        }
        response.setStatus(true);
        response.setMessage("Order deleted successfully");
        return response;
    }
    @GetMapping("/get/{id}")
    public Order getOrder(@PathVariable("id") String id){
        System.out.println("in get with id:"+id);
        return orderServiceImp.getOrder(id);
    }
    @GetMapping("/getPrice/{id}")
    public Double getOrderPrice(String id){
        System.out.println("in getPrice with id:"+id);
        return orderServiceImp.getOrder(id).getPrice();
    }
}


/*
    test simple
            {
                "id": "order1",
                "type": "simple",
                "products": [
                    {"id": "product1", "name": "Product 1", "price": 10.0, "available":20},
                    {"id": "product2", "name": "Product 2", "price": 20.0, "available":30}
                ]
            }

     */
        /*
        testCompound
        {
                "id": "order1",
                "type": "compound",
                "subOrders": [
                    {
                        "id": "order2",
                            "type": "simple",
                            "products": [
                        {"id": "product1", "name": "Product 1", "price": 10.0, "available":10},
                        {"id": "product2", "name": "Product 2", "price": 20.0, "available":20}
                    ]
                    },
                    {
                        "id": "order3",
                            "type": "simple",
                            "products": [
                        {"id": "product3", "name": "Product 3", "price": 30.0, "available":30}
                    ]
                    }
                ]

    }
    */