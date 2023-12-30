package com.example.demo.service;

import com.example.demo.Database;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class StatisticsService implements IStatisticsService {


    @Override
    public void addNotifiedEmail(String email) {
        Database.statistics.addNotifiedEmail(email);
    }

    @Override
    public void addNotifiedSMS(String phone) {
        Database.statistics.addNotifiedSMS(phone);
    }

    @Override
    public void addTemplateUsage(String template) {
        Database.statistics.addTemplateUsage(template);
    }

    @Override
    public String getMostNotifiedEmail() {
        HashMap<String, Integer> notifiedEmails = Database.statistics.getNotifiedEmails();
        return mostNotified(notifiedEmails);
    }

    @Override
    public String getMostNotifiedSMS() {
        HashMap<String, Integer> notifiedSMS = Database.statistics.getNotifiedSMS();
        return mostNotified(notifiedSMS);
    }

    @Override
    public String getMostUsedTemplate() {
        HashMap<String, Integer> templateUsage = Database.statistics.getTemplateUsage();
        return mostNotified(templateUsage);
    }

    private String mostNotified(HashMap<String, Integer> notified) {
        String mostNotified = "";
        int max = 0;
        for (String key : notified.keySet()) {
            if (notified.get(key) > max) {
                max = notified.get(key);
                mostNotified = key;
            }
        }
        return mostNotified;
    }
}
