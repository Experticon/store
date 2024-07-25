package com.example.store.controller.api;

import com.example.store.model.product;
import com.example.store.model.stock_mv;
import com.example.store.service.categoryService;
import com.example.store.service.productService;
import com.example.store.service.stock_mvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
/**
 * @class productController
 * Часть api, отвечающий за взаимодействие
 * с моделью "product". Отправляет и принимает json файлы.
 */
@RestController
@RequestMapping("/api/products")
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

    @GetMapping("")
    @ResponseBody
    public List<product> getAllProducts() {
        return pService.getAllProducts();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public product getProductById(@PathVariable UUID id) {
        return pService.getProductById(id);
    }

    @PostMapping("/product/create")
    public product createProduct(@RequestBody product newProduct) {
        product savedProduct = pService.createProduct(newProduct);
        if (newProduct.getQuantity() > 0) {
            stock_mv newStockMovement = new stock_mv();
            newStockMovement.setProduct(savedProduct);
            newStockMovement.setQuantity(newProduct.getQuantity());
            newStockMovement.setMovementType("IN");
            newStockMovement.setMovementDate(newProduct.getCreatedAt());
            stService.createStock_mv(newStockMovement);
        }
        return savedProduct;
    }

    @DeleteMapping("/product/delete/{id}")
    public void deleteProduct(@PathVariable UUID id) {
        pService.deleteProduct(id);
    }

    @PutMapping("/product/update")
    public product updateProduct(@RequestParam UUID id, @RequestBody product updatedProduct) {
        product prod = pService.getProductById(id);
        prod.setName(updatedProduct.getName());
        prod.setSku(updatedProduct.getSku());
        prod.setDescription(updatedProduct.getDescription());
        prod.setCategory(ctService.getCategoryById(updatedProduct.getCategory().getId()));
        prod.setPrice(updatedProduct.getPrice());
        prod.setQuantity(updatedProduct.getQuantity());
        return pService.updateProduct(prod);
    }
}