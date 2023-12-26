package com.example.demo.service;

import com.example.demo.model.Notifications.Notification;
import com.fasterxml.jackson.databind.JsonNode;

public interface INotificationService {
    public Notification createNotification(JsonNode user, String message, String type);

    public boolean addNotification(Notification notification);
    public boolean sendNotification();

}
