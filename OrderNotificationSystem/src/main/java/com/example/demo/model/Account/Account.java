package com.example.demo.model.Account;

public class Account {
    String username;
    String email;
    String phone;
    double balance;

    public Account(String username, String email, String phone, double balance) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void display(){
        System.out.println("Name: " + this.username);
        System.out.println("Balance: " + this.balance);
    }
}
