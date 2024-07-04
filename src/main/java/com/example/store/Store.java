package com.example.store;

import com.example.store.service.categoryService;
import com.example.store.service.productService;
import com.example.store.service.stock_mvService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Веб-сайт склада товаров
 * @author Абросимов Костя
 */
@SpringBootApplication
public class Store {


	@Autowired
	public Store() {
	}

	public static void main(String[] args) {
		SpringApplication.run(Store.class, args);
	}

}
