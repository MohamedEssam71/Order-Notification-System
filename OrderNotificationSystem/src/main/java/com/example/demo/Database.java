package com.example.demo;

import com.example.demo.model.Account.Account;
import com.example.demo.model.Notifications.Notification;
import com.example.demo.model.Order;
import com.example.demo.model.Product;

import java.util.HashMap;
import java.util.LinkedList;

public class Database {
    public static HashMap<String, Order> orderDB = new HashMap<>();
    public static HashMap<String, Account> accounts = new HashMap<>();
    public static HashMap<String, Product> products = new HashMap<>();
    public static LinkedList<Notification> notificationsQueue = new LinkedList<>();
}
