package com.example.demo.model.Notifications;

public abstract class SendCommand {
    String receiver;
    String message;
    SendService svc;

    public SendCommand(String receiver, String message, SendService svc) {
        this.receiver = receiver;
        this.message=message;
        this.svc = svc;
    }

    public abstract void execute();
}
