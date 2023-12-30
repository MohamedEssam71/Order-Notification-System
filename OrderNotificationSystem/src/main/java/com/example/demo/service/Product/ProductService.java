package com.example.demo.service.Product;

import com.example.demo.Database;
import com.example.demo.model.Product;
import com.example.demo.service.Product.IProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService implements IProductService {

    @Override
    public Product get(String serial) {
        return Database.products.get(serial);
    }

    @Override
    public ArrayList<Product> getAll() {
        return new ArrayList<>(Database.products.values());
    }

    @Override
    public Boolean checkAvailable(String serial, Integer quantity) {
        return get(serial).getAvailable() >= quantity;
    }

    @Override
    public void updateQuantity(String serial, Integer quantity) {
        Product product = Database.products.get(serial);
        product.setAvailable(product.getAvailable() + quantity);
    }

    @Override
    public Boolean productExists(String serial) {
        return Database.products.containsKey(serial);
    }
}
