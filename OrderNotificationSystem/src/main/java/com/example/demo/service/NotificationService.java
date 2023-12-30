package com.example.demo.service;

import com.example.demo.model.Account.Account;
import com.example.demo.model.Notifications.*;
import com.example.demo.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    IAccService accService;

    @Autowired
    IStatisticsService statisticsService;

    @Autowired
    MessageGenService msgSvc;

    @Override
    public Notification createNotification(String username, String message, String type) {
        SendService svc = null;
        SendCommand command = null;
        Account user = accService.getUser(username);
        switch (type) {
            case "SMS" -> {
                svc = new SendSMSService();
                command = new SendCommand(user.getPhone(), message, svc);
                statisticsService.addNotifiedSMS(user.getPhone());
            }
            case "Email" -> {
                svc = new SendEmailService();
                command = new SendCommand(user.getEmail(), message, svc);
                statisticsService.addNotifiedEmail(user.getEmail());
            }
        }
        return new Notification(command);
    }

    @Override
    public Boolean addNotification(NotificationChannel notificationChannel) {
        String user = notificationChannel.getUsername();
        String notificationMessageType = notificationChannel.getMessageType();
        String lang = notificationChannel.getLang();
        Map<String, Object> notificationMessageParams = notificationChannel.getMessageParams();

        String message = msgSvc.generate(notificationMessageType, notificationMessageParams, lang);
        statisticsService.addTemplateUsage(notificationMessageType);

        List<String> sendChannels = notificationChannel.getChannels();
        for (String sendChannel : sendChannels) {
            Notification notification = createNotification(user, message, sendChannel);
            Database.notificationsQueue.add(notification);
        }
        return true;
    }

    @Override
    public Boolean sendNotification() {
        if (Database.notificationsQueue.isEmpty()) {
            return false;
        }
        Notification notification = Database.notificationsQueue.poll();
        notification.send();
        return true;
    }
}
