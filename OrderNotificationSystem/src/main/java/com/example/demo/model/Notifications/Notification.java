package com.example.demo.model.Notifications;

public class Notification {
    SendCommand command;

    public Notification(SendCommand command) {
        this.command = command;
    }

    public void send() {
        command.execute();
    }
}
