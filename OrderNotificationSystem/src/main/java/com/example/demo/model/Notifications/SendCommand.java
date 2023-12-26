package com.example.demo.model.Notifications;

public class SendCommand implements Command {
    String receiver;

    String message;
    SendService svc;

    public SendCommand(String receiver, String message, SendService svc) {
        this.receiver = receiver;
        this.message = message;
        this.svc = svc;
    }

    @Override
    public void execute() {
        svc.send(receiver, message);
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
