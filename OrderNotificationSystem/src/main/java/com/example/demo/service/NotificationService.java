package com.example.demo.service;

import com.example.demo.model.Notifications.Notification;
import com.example.demo.Database;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements INotificationService {
    @Override
    public boolean addNotification(Notification notification) {
        Database.notificationsQueue.add(notification);
        return true;
    }

    @Override
    public boolean sendNotification() {
        if(Database.notificationsQueue.isEmpty()) {
            return false;
        }
        Notification notification = Database.notificationsQueue.poll();
        notification.send();
        // must simulate wait here somehow??
        return true;
    }
}
