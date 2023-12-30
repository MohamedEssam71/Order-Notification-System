package com.example.demo.controller;

import com.example.demo.model.Notifications.Notification;
import com.example.demo.model.Notifications.NotificationChannel;
import com.example.demo.model.Response;
import com.example.demo.service.INotificationService;
import com.example.demo.service.IStatisticsService;
import com.example.demo.service.MessageGenService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    INotificationService notSvc;

    @Autowired
    MessageGenService msgSvc;

    @Autowired
    IStatisticsService statisticsService;

    @PostMapping("/add")
    public Response add(@RequestBody NotificationChannel notificationChannel) {
        String user = notificationChannel.getUsername();
        String notificationMessageType = notificationChannel.getMessageType();
        Map<String, Object> notificationMessageParams = notificationChannel.getMessageParams();

        String message = msgSvc.generate(notificationMessageType, notificationMessageParams);
        statisticsService.addTemplateUsage(notificationMessageType);

        List<String> sendChannels = notificationChannel.getChannels();
        for (String sendChannel : sendChannels) {
            Notification notification = notSvc.createNotification(user, message, sendChannel);
            notSvc.addNotification(notification);
        }

        Response response = new Response();
        response.setStatus(true);
        response.setMessage("Notification added");
        return response;
    }
}
