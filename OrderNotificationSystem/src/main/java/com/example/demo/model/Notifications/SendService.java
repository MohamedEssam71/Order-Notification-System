package com.example.demo.model.Notifications;

public abstract interface SendService {
    public abstract void send(String receiver, String message);
}
