package com.example.demo.repository;

import com.example.demo.model.Account.Account;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.*;

@Repository
public class AccountRepository implements IRepository<String, Account> {
    HashMap<String, Account> accounts = new HashMap<>();
    @Override
    public void put(String key, Account account) {
        accounts.put(key, account);
    }
    @Override
    public Account get(String key) {
        return accounts.get(key);
    }

    @Override
    public Boolean containsKey(String id) {
        return accounts.containsKey(id);
    }

    @Override
    public ArrayList<Account> values() {
        return new ArrayList<>(accounts.values());
    }

}
