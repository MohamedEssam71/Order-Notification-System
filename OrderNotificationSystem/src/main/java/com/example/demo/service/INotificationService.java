package com.example.demo.service;

import com.example.demo.model.Notifications.Notification;

public interface INotificationService {
    public boolean addNotification(Notification notification);
    public boolean sendNotification();

}
