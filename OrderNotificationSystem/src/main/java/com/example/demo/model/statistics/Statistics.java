package com.example.demo.model.statistics;

import java.util.HashMap;

public class Statistics {
    private HashMap<String, Integer> notifiedEmails = new HashMap<>();
    private HashMap<String, Integer> notifiedSMS = new HashMap<>();
    private HashMap<String, Integer> templateUsage = new HashMap<>();

    public HashMap<String, Integer> getNotifiedEmails() {
        return notifiedEmails;
    }

    public HashMap<String, Integer> getNotifiedSMS() {
        return notifiedSMS;
    }

    public HashMap<String, Integer> getTemplateUsage() {
        return templateUsage;
    }

    public void addNotifiedEmail(String email) {
        if (notifiedEmails.containsKey(email)) {
            notifiedEmails.put(email, notifiedEmails.get(email) + 1);
        } else {
            notifiedEmails.put(email, 1);
        }
    }

    public void addNotifiedSMS(String phone) {
        if (notifiedSMS.containsKey(phone)) {
            notifiedSMS.put(phone, notifiedSMS.get(phone) + 1);
        } else {
            notifiedSMS.put(phone, 1);
        }
    }

    public void addTemplateUsage(String template) {
        if (templateUsage.containsKey(template)) {
            templateUsage.put(template, templateUsage.get(template) + 1);
        } else {
            templateUsage.put(template, 1);
        }
    }
}
