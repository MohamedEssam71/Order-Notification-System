package com.example.demo.model.Notifications;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.*;

public class NotificationChannel {
    JsonNode user;
    String messageType;
    Map<String, Object> messageParams;
    List<String> channels;

    public NotificationChannel(JsonNode user, String messageType, Map<String, Object> messageParams, List<String> channels) {
        this.user = user;
        this.messageType = messageType;
        this.messageParams = messageParams;
        this.channels = channels;
    }

    public JsonNode getUser() {
        return user;
    }

    public void setUser(JsonNode user) {
        this.user = user;
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
