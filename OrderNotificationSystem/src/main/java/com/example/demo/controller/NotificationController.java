package com.example.demo.controller;

import com.example.demo.model.Notifications.Notification;
import com.example.demo.model.Response;
import com.example.demo.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    INotificationService service;

    @PostMapping("/add")
    public Response addSimpleOrder(@RequestBody Notification notification) {
        return addNotification(notification);
    }

    @GetMapping("/send")
    public Response send() {
        return sendNotification();
    }

    public Response addNotification(Notification notification) {
        Response response = new Response();
        boolean status = service.addNotification(notification);
        if(status){
            response.setStatus(true);
            response.setMessage("Notification added");
        }
        else{
            response.setStatus(false);
            response.setMessage("Error adding notification");
        }
        return response;
    }

    public Response sendNotification() {
        Response response = new Response();
        boolean status = service.sendNotification();
        if(status){
            response.setStatus(true);
            response.setMessage("Notification sent");
        }
        else{
            response.setStatus(false);
            response.setMessage("Error sending notification");
        }
        return response;
    }
}
