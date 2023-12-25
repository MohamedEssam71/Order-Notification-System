package com.example.demo;

import com.example.demo.model.Notifications.Notification;
import com.example.demo.model.Order;

import java.util.HashMap;
import java.util.LinkedList;

public class Database {
    public static HashMap<String, Order> orderDB = new HashMap<String,Order>();
    public static LinkedList<Notification> notificationsQueue = new LinkedList<>();
}
