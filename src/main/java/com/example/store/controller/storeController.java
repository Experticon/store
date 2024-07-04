package com.example.store.controller;

import com.example.store.model.category;
import com.example.store.repository.categoryRep;
import com.example.store.service.categoryService;
import com.example.store.model.product;
import com.example.store.service.productService;
import com.example.store.model.stock_mv;
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
 * @class storeController
 * Контроллер, отвечающий за чтение информации и её показ
 * В ней есть вкладки:
 * "Home" - табличное чтение данных (Read).
 */
@Controller
public class storeController {

    private final categoryService ctService;
    private final productService pService;
    private final stock_mvService stService;

    public storeController(categoryService ctService, productService pService, stock_mvService stService) {
        this.ctService = ctService;
        this.pService = pService;
        this.stService = stService;
    }

    @GetMapping("/")
    public String Store(Model model) {
        List<category> categories = ctService.getAllCategories();
        List<product> products = pService.getAllProducts();
        List<stock_mv> stock_mv = stService.getAllStock_mv();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("stock_mv", stock_mv);
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
        model.addAttribute("stock_mv", stockMovement);
        model.addAttribute("products", products);
        return "edit_stock_mv";
    }
}
