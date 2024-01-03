package com.example.demo;

import com.example.demo.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OrderNotificationSystemApplication {

	public static void main(String[] args) {
		Product p1 = new Product("000", "Snickers",5.0,3);
		Product p2 = new Product("001", "Twinkies",10.0,1);
		Product p3 = new Product("002", "Cadbury",20.0,2);

		Database.products.put(p1.getSerialNumber(), p1);
		Database.products.put(p2.getSerialNumber(), p2);
		Database.products.put(p3.getSerialNumber(), p3);

		SpringApplication.run(OrderNotificationSystemApplication.class, args);
	}

}
