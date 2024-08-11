package com.example.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Веб-сайт склада товаров
 * @author Абросимов Костя
 */
@SpringBootApplication
public class Store {
	public Store() {
	}

	public static void main(String[] args) {
		SpringApplication.run(Store.class, args);
	}

}
