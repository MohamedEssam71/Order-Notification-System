package com.example.demo.repository;

import com.example.demo.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.stereotype.*;

@Repository
public class ProductRepository implements IRepository<String, Product> {
    HashMap<String, Product> productDB = new HashMap<>();

    @Override
    public void put(String key, Product product) {
        productDB.put(key, product);
    }

    @Override
    public Product get(String key) {
        return productDB.get(key);
    }

    @Override
    public Boolean containsKey(String id) {
        return productDB.containsKey(id);
    }

    @Override
    public ArrayList<Product> values() {
        return new ArrayList<>(productDB.values());
    }
}
