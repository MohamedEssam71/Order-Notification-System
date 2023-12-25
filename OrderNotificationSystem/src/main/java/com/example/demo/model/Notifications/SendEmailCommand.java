package com.example.demo.model.Notifications;

public class SendEmailCommand extends SendCommand{
    public SendEmailCommand(String receiver, String message, SendEmailService svc) {
        super(receiver, message, svc);
    }

    @Override
    public void execute() {
        svc.send(receiver, message);
    }
}
