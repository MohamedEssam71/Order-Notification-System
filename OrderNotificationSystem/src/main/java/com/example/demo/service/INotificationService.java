package com.example.demo.service;

import com.example.demo.model.Account.Account;
import com.example.demo.model.Notifications.Notification;
import com.example.demo.model.Notifications.NotificationChannel;
import com.fasterxml.jackson.databind.JsonNode;

public interface INotificationService {
    public Notification createNotification(String username, String message, String type);

    public Boolean addNotification(NotificationChannel notificationChannel);
    public Boolean sendNotification();

}
