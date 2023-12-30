package com.example.demo.service;

import com.example.demo.Database;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificationQueueScheduler {
    @Scheduled(cron = "*/5 * * * * *")
    public void sendNotification(){
        NotificationService services = new NotificationService();
        if(services.sendNotification()){
            System.out.println("Notification sent");
        }
    }
}
