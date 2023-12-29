package com.example.demo.model.Notifications;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.*;

public class NotificationChannel {
    String username;
    String messageType;
    Map<String, Object> messageParams;
    List<String> channels;

    public NotificationChannel(String user, String messageType, Map<String, Object> messageParams, List<String> channels) {
        this.username = user;
        this.messageType = messageType;
        this.messageParams = messageParams;
        this.channels = channels;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Map<String, Object> getMessageParams() {
        return messageParams;
    }

    public void setMessageParams(Map<String, Object> messageParams) {
        this.messageParams = messageParams;
    }

    public List<String> getChannels() {
        return channels;
    }

    public void setChannels(List<String> channels) {
        this.channels = channels;
    }
}
