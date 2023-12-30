package com.example.demo.service.Product;

import com.example.demo.model.Product;

import java.util.ArrayList;

public interface IProductService {
    public Product get(String serial);
    public ArrayList<Product> getAll();
    public Boolean checkAvailable(String serial, Integer quantity);

    public void updateQuantity(String serial, Integer quantity);

    public Boolean productExists(String serial);

}
