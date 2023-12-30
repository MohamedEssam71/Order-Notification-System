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
        if (!orderService.validateOrderItemsAvailable(order)) {
            Response response = new Response();
            response.setStatus(false);
            response.setMessage("Ordered Items are unavailable");
            return response;
        }

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
        if (!orderService.validateOrderItemsAvailable(order)) {
            Response response = new Response();
            response.setStatus(false);
            response.setMessage("Ordered Items are unavailable");
            return response;
        }

        boolean allValid = true;
        for (Order sub : order.getOrders()) {
            allValid &= orderService.validateOrder(sub);
        }
        double personFees = orderFees / order.getOrders().size();
        if (allValid) {
            for (Order sub : order.getOrders()) {
                orderService.addOrder(sub);
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

    @GetMapping("/get/{id}")
    public Order getOrder(@PathVariable("id") String id) {
        return orderService.getOrder(id);
    }

    @GetMapping("/getPrice/{id}")
    public Double getOrderPrice(@PathVariable("id") String id) {
        Order order = orderService.getOrder(id);
        return orderService.calculateOrderPrice(order);
    }

    @GetMapping("/ship/{id}")
    public Response shipOrder(@PathVariable("id") String id) {
        Response response = new Response();

        Boolean exists = orderService.orderExists(id);
        if (!exists) {
            response.setStatus(false);
            response.setMessage("Order doesn't exist");
            return response;
        }

        Boolean status = orderService.shipOrder(id);
        if (status) {
            response.setStatus(true);
            response.setMessage("Order shipping");
        } else {
            response.setStatus(false);
            response.setMessage("Can't start shipping order");
        }
        return response;
    }

    @GetMapping("/refund/compound/{id}")
    public Response refundCompound(@PathVariable("id") String id) {
        Response response = new Response();

        Boolean exists = orderService.orderExists(id);
        if (!exists) {
            response.setStatus(false);
            response.setMessage("Order doesn't exist");
            return response;
        }

        CompoundOrder order = (CompoundOrder) orderService.getOrder(id);

        if (order.getStatus() == OrderStatus.REFUNDED) {
            response.setStatus(false);
            response.setMessage("Order Already refunded");
            return response;
        }

        Double personFees = orderFees / order.getOrders().size();
        for (Order sub : order.getOrders()) {
            orderService.refundOrder(sub, personFees);
        }
        order.setStatus(OrderStatus.REFUNDED);

        if (order.getStatus() == OrderStatus.SHIPPED) {
            response.setStatus(true);
            response.setMessage("Only order price refunded");
        } else {
            response.setStatus(true);
            response.setMessage("Order fully refunded");
        }
        return response;
    }

    @GetMapping("/refund/simple/{id}")
    public Response refundSimple(@PathVariable("id") String id) {
        Response response = new Response();

        Boolean exists = orderService.orderExists(id);
        if (!exists) {
            response.setStatus(false);
            response.setMessage("Order doesn't exist");
            return response;
        }

        Order order = orderService.getOrder(id);
        Boolean refunded = orderService.refundOrder(order, orderFees);
        if (refunded) {
            if (order.getStatus() == OrderStatus.SHIPPED) {
                response.setStatus(true);
                response.setMessage("Only order price refunded");
            } else {
                response.setStatus(true);
                response.setMessage("Order fully refunded");
            }
        } else {
            response.setStatus(false);
            response.setMessage("Order Already refunded");
        }
        return response;
    }
}