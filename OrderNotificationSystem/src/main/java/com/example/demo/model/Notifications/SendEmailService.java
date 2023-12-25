package com.example.demo.model.Notifications;

public class SendEmailService implements SendService{

    @Override
    public void send(String receiver, String message) {
        // send email logic

        // as placeholder will print email to console
        System.out.println("Email to: " + receiver);
        System.out.println(message);
        System.out.println("Best regards");
    }
}
