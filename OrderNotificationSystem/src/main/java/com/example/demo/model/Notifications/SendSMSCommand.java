package com.example.demo.model.Notifications;

public class SendSMSCommand extends SendCommand{
    public SendSMSCommand(String receiver, String message, SendSMSService svc) {
        super(receiver, message, svc);
    }

    @Override
    public void execute() {
        svc.send(receiver, message);
    }
}
