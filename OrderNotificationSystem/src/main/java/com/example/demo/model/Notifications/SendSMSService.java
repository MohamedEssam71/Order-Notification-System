package com.example.demo.model.Notifications;

public class SendSMSService implements SendService{

    @Override
    public void send(String receiver, String message) {
        // send sms logic

        // as placeholder will print sms to console
        System.out.println("SMS to: " + receiver);
        System.out.println(message);
    }
}
