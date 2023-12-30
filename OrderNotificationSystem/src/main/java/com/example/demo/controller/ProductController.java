package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.Product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    IProductService productService;

    @GetMapping("/get/{serial}")
    public Product get(@PathVariable("serial") String serial) {
        return productService.get(serial);
    }

    @GetMapping("/getAll")
    public ArrayList<Product> getAll() {
        return productService.getAll();
    }
}
