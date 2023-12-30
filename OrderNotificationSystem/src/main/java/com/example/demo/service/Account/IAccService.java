package com.example.demo.service.Account;

import com.example.demo.model.Account.Account;

public interface IAccService {

    public boolean createUser(String name, String email, String phone, double balance);
    public boolean createUser(Account acc);

    public boolean userExists(String name);

    public Account getUser(String name);

    public boolean updateBalance(String name, double amount);

}
