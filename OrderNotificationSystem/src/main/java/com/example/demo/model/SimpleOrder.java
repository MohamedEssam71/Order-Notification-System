package com.example.demo.model;

import java.util.ArrayList;

public class SimpleOrder extends Order{
    private ArrayList<Product> products;
    String type;

    public SimpleOrder(String id, String type,ArrayList<Product>products){
        super(id);
        this.type = type;
        this.products = products;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Double getPrice() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    @Override
    public ArrayList<Product> getProducts() {
        return products;
    }

    @Override
    public void display() {
        System.out.print("ID: " + this.id + " Type: " + this.type);
        for(int i = 0; i < products.size(); ++i){
            System.out.print("\n\tSerial: " + products.get(i).serialNumber
            + "\n\tName: " + products.get(i).name + "\n\tPrice: " + products.get(i).price
            + "\n\tAvailable: " + products.get(i).available + "\n\t ==========================");
        }
        System.out.println();
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
