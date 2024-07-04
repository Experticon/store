package com.example.store.controller;

import com.example.store.model.category;
import com.example.store.repository.categoryRep;
import com.example.store.model.product;
import com.example.store.model.stock_mv;
import com.example.store.service.categoryService;
import com.example.store.service.productService;
import com.example.store.service.stock_mvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
/**
 * @class productController
 * Каждый контролер написан под каждую модель отдельно.
 * Они работают, когда пользователь нажимает на:
 * кнопки создания на странице "Create"
 * кнопки edit и delete на каждом элементе на странице "Home"
 */
@Controller
public class productController {

    private final categoryService ctService;
    private final productService pService;
    private final stock_mvService stService;


    @Autowired
    public productController(categoryService ctService, productService pService, stock_mvService stService) {
        this.ctService = ctService;
        this.pService = pService;
        this.stService = stService;
    }

    @GetMapping("/products")
    @ResponseBody
    public List<product> getAllProducts() {
        return pService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    @ResponseBody
    public product getProductById(@PathVariable UUID id) {
        return pService.getProductById(id);
    }

    @PostMapping("/product/create")
    public String createProduct(@RequestParam String name,
                                @RequestParam String sku,
                                @RequestParam String description,
                                @RequestParam UUID category,
                                @RequestParam BigDecimal price,
                                @RequestParam int quantity,
                                @RequestParam String createdAt) {
        product newProduct = new product();
        newProduct.setName(name);
        newProduct.setSku(sku);
        newProduct.setDescription(description);
        newProduct.setCategory(ctService.getCategoryById(category));
        newProduct.setPrice(price);
        newProduct.setQuantity(quantity);
        newProduct.setLastQuantityUpdate(LocalDateTime.parse(createdAt)); // одно и то же время с созданием
        newProduct.setCreatedAt(LocalDateTime.parse(createdAt));

        product savedProduct = pService.createProduct(newProduct);
        if (quantity > 0) {
            stock_mv newStockMovement = new stock_mv();
            newStockMovement.setProduct(savedProduct);
            newStockMovement.setQuantity(quantity);
            newStockMovement.setMovementType("IN");
            newStockMovement.setMovementDate(LocalDateTime.parse(createdAt));
            stService.createStock_mv(newStockMovement);
        }
        return "redirect:/create";
    }
    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable UUID id) {
        pService.deleteProduct(id);
        return "redirect:/";
    }
    @PostMapping("/product/update")
    public String updateProduct(@RequestParam UUID id,
                                @RequestParam String name,
                                @RequestParam String sku,
                                @RequestParam String description,
                                @RequestParam UUID category,
                                @RequestParam BigDecimal price,
                                @RequestParam int quantity) {
        product prod = pService.getProductById(id);
        prod.setName(name);
        prod.setSku(sku);
        prod.setDescription(description);
        prod.setCategory(ctService.getCategoryById(category));
        prod.setPrice(price);
        prod.setQuantity(quantity);
        pService.updateProduct(prod);
        return "redirect:/";
    }
}