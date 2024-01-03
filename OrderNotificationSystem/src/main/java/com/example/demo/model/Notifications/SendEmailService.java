package com.example.demo.model.Notifications;

public class SendEmailService implements SendService{

    @Override
    public void send(String receiver, String message) {
        // send email logic

        // as placeholder will print email to console
        System.out.println("\u001B[33m" + "Email to: "+ "\u001B[0m" + receiver);
        System.out.println(message);
        System.out.println("Best regards");
    }
}
