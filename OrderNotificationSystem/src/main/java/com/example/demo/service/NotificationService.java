package com.example.demo.service;

import com.example.demo.model.Notifications.*;
import com.example.demo.Database;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements INotificationService {
    @Override
    public Notification createNotification(JsonNode user, String message, String type) {
        SendService svc = null;
        SendCommand command = null;
        switch (type) {
            case "SMS" -> {
                svc = new SendSMSService();
                command = new SendCommand(user.get("phone").textValue(), message, svc);
            }
            case "Email" -> {
                svc = new SendEmailService();
                command = new SendCommand(user.get("email").textValue(), message, svc);
            }
        }
        return new Notification(command);
    }

    @Override
    public boolean addNotification(Notification notification) {
        Database.notificationsQueue.add(notification);
        return true;
    }

    @Override
    public boolean sendNotification() {
        if (Database.notificationsQueue.isEmpty()) {
            return false;
        }
        Notification notification = Database.notificationsQueue.poll();
        notification.send();
        // must simulate wait here somehow??
        return true;
    }
}
