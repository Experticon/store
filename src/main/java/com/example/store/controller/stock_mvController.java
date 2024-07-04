package com.example.store.controller;

import com.example.store.model.product;
import com.example.store.model.stock_mv;
import com.example.store.service.productService;
import com.example.store.service.stock_mvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
/**
 * @class stock_mvController
 * Каждый контролер написан под каждую модель отдельно.
 * Они работают, когда пользователь нажимает на:
 * кнопки создания на странице "Create"
 * кнопки edit и delete на каждом элементе на странице "Home"
 */
@Controller
public class stock_mvController {

    private final productService pService;
    private final stock_mvService stService;



    @Autowired
    public stock_mvController(productService pService, stock_mvService stService) {
        this.pService = pService;
        this.stService = stService;
    }


    @GetMapping("/stock_mv")
    @ResponseBody
    public List<stock_mv> getAllStock_mv() {
        return stService.getAllStock_mv();
    }


    @GetMapping("/stock_mv/{id}")
    public stock_mv getStock_mvById(@PathVariable UUID id) {
        return stService.getStock_mvById(id);
    }


    @PostMapping("/stock-movement/create")
    public String createStockMovement(@RequestParam UUID product,
                                      @RequestParam int quantity,
                                      @RequestParam String movementType,
                                      @RequestParam String movementDate) {
        stock_mv newStockMovement = new stock_mv();
        newStockMovement.setProduct(pService.getProductById(product));
        newStockMovement.setQuantity(quantity);
        newStockMovement.setMovementType(movementType);
        newStockMovement.setMovementDate(LocalDateTime.parse(movementDate));
        stService.createStock_mv(newStockMovement);
        return "redirect:/create";
    }
    // Удаление поставки
    @PostMapping("/stock_mv/delete/{id}")
    public String deleteStock_mv(@PathVariable UUID id) {
        stService.deleteStock_mv(id);
        return "redirect:/";
    }
    @PostMapping("/stock_mv/update")
    public String updateStock_mv(@RequestParam UUID id,
                                 @RequestParam UUID productId,
                                 @RequestParam int quantity,
                                 @RequestParam String movementType,
                                 @RequestParam String movementDate) {
        stock_mv stockMovement = stService.getStock_mvById(id);
        stockMovement.setProduct(pService.getProductById(productId));
        stockMovement.setQuantity(quantity);
        stockMovement.setMovementType(movementType);
        stockMovement.setMovementDate(LocalDateTime.parse(movementDate));
        stService.updateStock_mv(stockMovement);
        return "redirect:/";
    }
}
