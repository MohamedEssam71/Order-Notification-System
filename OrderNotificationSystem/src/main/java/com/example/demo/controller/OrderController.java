package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.model.Order.*;
import com.example.demo.service.Notification.IStatisticsService;
import com.example.demo.model.Notifications.NotificationChannel;
import com.example.demo.service.Notification.INotificationService;
import com.example.demo.service.Order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    IOrderService orderService;

    @Autowired
    INotificationService notificationService;
    @Autowired
    IStatisticsService statisticsService;

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
        orderService.setOrderType(order.getId(), OrderType.SIMPLE);
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
                orderService.setOrderType(sub.getId(), OrderType.SIMPLE);
                orderService.addOrder(sub);
                orderService.payOrder(sub, personFees);
            }
        } else {
            Response response = new Response();
            response.setStatus(false);
            response.setMessage("One of the orders is invalid");
            return response;
        }
        orderService.setOrderType(order.getId(), OrderType.COMPOUND);
        return addOrder(order);
    }

    public Response addOrder(Order order) {
        Response response = new Response();
        Integer status = orderService.addOrder(order);
        if (status == 1) {
            NotificationChannel notificationChannel = createNotificationChannel(order, "order-created", "en");
            notificationService.addNotification(notificationChannel);
            statisticsService.addTemplateUsage("order-created");
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
            Order order = orderService.getOrder(id);
            NotificationChannel notificationChannel = createNotificationChannel(order, "order-shipped", "en");
            notificationService.addNotification(notificationChannel);
            statisticsService.addTemplateUsage("order-shipped");

            response.setStatus(true);
            response.setMessage("Order shipping");
        } else {
            response.setStatus(false);
            response.setMessage("Can't start shipping order");
        }
        return response;
    }
  
    private NotificationChannel createNotificationChannel(Order order, String type, String lang) {
        Map<String, Object> notificationParams = new HashMap<>() {
            {
                put("id", order.getId());
                put("price", orderService.calculateOrderPrice(order));
            }
        };
        ArrayList<String> channels = new ArrayList<>() {{
            add("SMS");
            add("Email");
        }};
        return new NotificationChannel(order.getBuyerName(),
                type, lang, notificationParams, channels);
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

        if(orderService.getOrderType(id) != OrderType.COMPOUND){
            response.setStatus(false);
            response.setMessage("Invalid order type!");
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

        if(orderService.getOrderType(id) != OrderType.SIMPLE){
            response.setStatus(false);
            response.setMessage("Invalid order type!");
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