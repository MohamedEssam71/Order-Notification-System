package com.example.demo;

import com.example.demo.model.Notifications.Notification;
import com.example.demo.model.statistics.Statistics;
import com.example.demo.repository.*;

import java.util.LinkedList;

public class Database {
    public static OrderRepository orderDB = new OrderRepository();
    public static OrderTypeRepository orderType = new OrderTypeRepository();
    public static AccountRepository accounts = new AccountRepository();
    public static ProductRepository products = new ProductRepository();
    public static LinkedList<Notification> notificationsQueue = new LinkedList<>();

    public static Statistics statistics = new Statistics();
}
