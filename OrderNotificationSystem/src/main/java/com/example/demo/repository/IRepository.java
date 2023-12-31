package com.example.demo.repository;

import java.util.ArrayList;


public interface IRepository<Key, Type> {
    void put(Key key, Type type);
    Type get(Key key);

    Boolean containsKey(Key id);

    ArrayList<Type> values();
}
