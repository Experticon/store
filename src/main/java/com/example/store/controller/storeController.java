package com.example.store.controller;

import com.example.store.model.category;
import com.example.store.service.categoryService;
import com.example.store.model.product;
import com.example.store.service.productService;
import com.example.store.model.stock_mv;
import com.example.store.service.stock_mvService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
/**
 * @class storeController
 * Контроллер, отвечающий за показ веб-страниц проекта.
 */
@Controller
@RequestMapping("/")
public class storeController {

    private final categoryService ctService;
    private final productService pService;
    private final stock_mvService stService;

    @Value("${spring.profiles.active}")
    private String activeProfile;


    public storeController(categoryService ctService, productService pService, stock_mvService stService) {
        this.ctService = ctService;
        this.pService = pService;
        this.stService = stService;
    }

    @ModelAttribute("activeProfile")
    public String getActiveProfile() {
        return activeProfile;
    }

    @GetMapping("/")
    public String Store() {
        return "store"; // Имя шаблона FreeMarker
    }

    @GetMapping("/create")
    public String createElem(Model model) {
        List<category> categories = ctService.getAllCategories();
        List<product> products = pService.getAllProducts();
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        return "createElem";
    }
    @GetMapping("/category/edit/{id}")
    public String editCategory(@PathVariable UUID id, Model model) {
        category cat = ctService.getCategoryById(id);
        model.addAttribute("category", cat);
        return "edit_category";
    }
    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable UUID id, Model model) {
        product prod = pService.getProductById(id);
        List<category> categories = ctService.getAllCategories();
        model.addAttribute("product", prod);
        model.addAttribute("categories", categories);
        return "edit_product";
    }
    @GetMapping("/stock_mv/edit/{id}")
    public String editStock_mv(@PathVariable UUID id, Model model) {
        stock_mv stockMovement = stService.getStock_mvById(id);
        List<product> products = pService.getAllProducts();

        // Форматируем дату и время
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String formattedMovementDate = stockMovement.getMovementDate().format(formatter);

        // Добавляем отформатированную дату в модель
        model.addAttribute("stock_mv", stockMovement);
        model.addAttribute("products", products);
        model.addAttribute("formattedMovementDate", formattedMovementDate);

        return "edit_stock_mv";
    }
}
