package com.example.demo.service;

import com.example.demo.model.Account.Account;
import com.example.demo.model.Notifications.*;
import com.example.demo.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    IAccService accService;
    @Override
    public Notification createNotification(String username, String message, String type) {
        SendService svc = null;
        SendCommand command = null;
        Account user = accService.getUser(username);
        switch (type) {
            case "SMS" -> {
                svc = new SendSMSService();
                command = new SendCommand(user.getPhone(), message, svc);
            }
            case "Email" -> {
                svc = new SendEmailService();
                command = new SendCommand(user.getEmail(), message, svc);
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
        return true;
    }
}
