package com.example.store.controller;

import com.example.store.model.category;
import com.example.store.model.product;
import com.example.store.model.stock_mv;
import com.example.store.service.categoryService;
import com.example.store.service.productService;
import com.example.store.service.stock_mvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * @class storeJsonController
 * Контроллер, показывающий информацию в формате JSON
 * Три вкладки под каждую модель:
 * Category, Product, Stock Movements
 */
@RestController
@RequestMapping("/api")
public class storeJsonController {

    private final categoryService ctService;
    private final productService pService;
    private final stock_mvService stService;

    @Autowired
    public storeJsonController(categoryService ctService, productService pService, stock_mvService stService) {
        this.ctService = ctService;
        this.pService = pService;
        this.stService = stService;
    }

    @GetMapping("/categories")
    public List<category> getAllCategories() {
        return ctService.getAllCategories();
    }

    @GetMapping("/products")
    public List<product> getAllProducts() {
        return pService.getAllProducts();
    }

    @GetMapping("/stock_movements")
    public List<stock_mv> getAllStockMovements() {
        return stService.getAllStock_mv();
    }
}