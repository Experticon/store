package com.example.store.controller.api;

import com.example.store.model.product;
import com.example.store.model.stock_mv;
import com.example.store.service.productService;
import com.example.store.service.stock_mvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
/**
 * @class stock_mvController
 * Каждый контролер написан под каждую модель отдельно.
 * Часть api, отвечающий за взаимодействие
 * с моделью "stock_mv". Отправляет и принимает json файлы.
 */
@RestController
@RequestMapping("/api/stock_mv")
public class stock_mvController {

    private final productService pService;
    private final stock_mvService stService;

    @Autowired
    public stock_mvController(productService pService, stock_mvService stService) {
        this.pService = pService;
        this.stService = stService;
    }

    @GetMapping("")
    @ResponseBody
    public List<stock_mv> getAllStockMovements() {
        return stService.getAllStock_mv();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public stock_mv getStock_mvById(@PathVariable UUID id) {
        return stService.getStock_mvById(id);
    }

    @PostMapping("/stock/create")
    @ResponseBody
    public stock_mv createStockMovement(@RequestBody stock_mv newStockMovement) {
        product prod = pService.getProductById(newStockMovement.getProduct().getId());
        newStockMovement.setProduct(prod);
        return stService.createStock_mv(newStockMovement);
    }

    @DeleteMapping("/stock/delete/{id}")
    public void deleteStock_mv(@PathVariable UUID id) {
        stService.deleteStock_mv(id);
    }

    @PutMapping("/stock/update")
    @ResponseBody
    public stock_mv updateStock_mv(@RequestParam UUID id, @RequestBody stock_mv updatedStockMovement) {
        stock_mv stockMovement = stService.getStock_mvById(id);
        stockMovement.setProduct(pService.getProductById(updatedStockMovement.getProduct().getId()));
        stockMovement.setQuantity(updatedStockMovement.getQuantity());
        stockMovement.setMovementType(updatedStockMovement.getMovementType());
        stockMovement.setMovementDate(LocalDateTime.parse(updatedStockMovement.getMovementDate().toString()));
        return stService.updateStock_mv(stockMovement);
    }
}
