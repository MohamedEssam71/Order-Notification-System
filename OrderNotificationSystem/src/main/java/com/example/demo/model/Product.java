package com.example.demo.model;

public class Product {
    String serialNumber, name, vendor, category;
    Double price;
    Integer available;
   public Product(String serialNumber, String name, Double price, Integer available){
        this.serialNumber = serialNumber;
        this.name = name;
        this.price = price;
        this.available = available;
    }

    public Double getPrice() {
        return price;
    }
}
