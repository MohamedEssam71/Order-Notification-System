package com.example.demo.service;

import com.example.demo.Database;
import com.example.demo.model.Account.Account;
import org.springframework.stereotype.Service;

@Service
public class AccService implements IAccService {
    @Override
    public boolean createUser(String name, String email, String phone, double balance) {
        Account newAcc = new Account(name, email, phone, balance);
        return createUser(newAcc);
    }

    @Override
    public boolean createUser(Account acc) {
        if (userExists(acc.getUsername())) {
            return false;
        }
        Database.accounts.put(acc.getUsername(), acc);
        return true;
    }

    @Override
    public boolean userExists(String name) {
        return Database.accounts.containsKey(name);
    }

    @Override
    public Account getUser(String name) {
        if (!userExists(name)) {
            return null;
        }
        return Database.accounts.get(name);
    }

    @Override
    public boolean updateBalance(String name, double amount) {
        Account acc = getUser(name);
        if (acc == null) {
            return false;
        }
        if (acc.getBalance() + amount < 0) {
            return false;
        }
        double newBalance = acc.getBalance() + amount;
        acc.setBalance(newBalance);
        return true;
    }
}
