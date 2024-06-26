package com.example.demo.service.Notification;

public interface IStatisticsService {

    void addNotifiedEmail(String email);

    void addNotifiedSMS(String phone);

    void addTemplateUsage(String template);

    String getMostNotifiedEmail();

    String getMostNotifiedSMS();

    String getMostUsedTemplate();
}
