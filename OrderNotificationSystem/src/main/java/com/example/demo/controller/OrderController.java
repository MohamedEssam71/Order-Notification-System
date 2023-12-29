package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    final double orderFees = 50.0;

    @PostMapping("/add/simpleOrder")
    public Response addSimpleOrder(@RequestBody SimpleOrder order) {
        if (orderService.validateOrder(order)) {
            orderService.payOrder(order, orderFees);
        } else {
            Response response = new Response();
            response.setStatus(false);
            response.setMessage("Order is invalid");
            return response;
        }
        return addOrder(order);
    }

    @PostMapping("/add/compoundOrder")
    public Response addCompoundOrder(@RequestBody CompoundOrder order) {
        boolean allValid = true;
        for (Order sub : order.getOrders()) {
            allValid &= orderService.validateOrder(sub);
        }

        double personFees = orderFees/order.getOrders().size();

        if (allValid) {
            for (Order sub : order.getOrders()) {
                orderService.payOrder(sub, personFees);
            }
        } else {
            Response response = new Response();
            response.setStatus(false);
            response.setMessage("One of the orders is invalid");
            return response;
        }
        return addOrder(order);
    }

    public Response addOrder(Order order) {
        Response response = new Response();
        Integer status = orderService.addOrder(order);
        if (status == 1) {
            response.setStatus(true);
            response.setMessage("Order added successfully");
        } else {
            response.setStatus(false);
            if (status == 0) {
                response.setMessage("Order already exists");
            } else if (status == -1) {
                response.setMessage("No accounts with this buyer name");
            }
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public Response removeOrder(@PathVariable("id") String id) {
        boolean res = orderService.removeOrder(id);
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
    public Order getOrder(@PathVariable("id") String id) {
        return orderService.getOrder(id);
    }

    @GetMapping("/getPrice/{id}")
    public Double getOrderPrice(@PathVariable("id") String id) {
        return orderService.getOrder(id).getPrice();
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