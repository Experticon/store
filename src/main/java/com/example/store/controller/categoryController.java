package com.example.store.controller;

import com.example.store.model.category;
import com.example.store.service.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
/**
 * @class category
 * Каждый контролер написан под каждую модель отдельно.
 * Они работают, когда пользователь нажимает на:
 * кнопки создания на странице "Create"
 * кнопки edit и delete на каждом элементе на странице "Home"
 */
@Controller
public class categoryController {

    private final categoryService ctService;


    @Autowired
    public categoryController(categoryService ctService) {
        this.ctService = ctService;
    }

    @GetMapping("/categories")
    @ResponseBody
    public List<category> getAllCategories() {
        return ctService.getAllCategories();
    }


    @GetMapping("/categories/{id}")
    @ResponseBody
    public category getCategoryById(@PathVariable UUID id) {
        return ctService.getCategoryById(id);
    }


    @PostMapping("/category/create")
    public String createCategory(@RequestParam String name) {
        category newCategory = new category();
        newCategory.setName(name);
        ctService.createCategory(newCategory);
        return "redirect:/create";
    }
    @PostMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable UUID id) {
        ctService.deleteCategory(id);
        return "redirect:/";
    }
    @PostMapping("/category/update")
    public String updateCategory(@RequestParam UUID id,
                                 @RequestParam String name) {
        category cat = ctService.getCategoryById(id);
        cat.setName(name);
        ctService.updateCategory(cat);
        return "redirect:/";
    }
}
