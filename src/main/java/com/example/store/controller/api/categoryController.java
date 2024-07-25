package com.example.store.controller.api;

import com.example.store.model.category;
import com.example.store.service.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @class category
 * Часть api, отвечающий за взаимодействие
 * с моделью "category". Отправляет и принимает json файлы.
 */
@RestController
@RequestMapping("/api/categories")
public class categoryController {

    private final categoryService ctService;


    @Autowired
    public categoryController(categoryService ctService) {
        this.ctService = ctService;
    }

    @GetMapping("")
    @ResponseBody
    public List<category> getAllCategories() {
        return ctService.getAllCategories();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public category getCategoryById(@PathVariable UUID id) {
        return ctService.getCategoryById(id);
    }

    @GetMapping("/name/{name}")
    @ResponseBody
    public category getCategoryByName(@PathVariable String name) {
        return ctService.getCategoryByName(name);
    }


    @PostMapping("/category/create")
    public category createCategory(@RequestBody category requestCategory) {
        return ctService.createCategory(requestCategory);
    }

    @DeleteMapping("/category/delete/{id}")
    public void deleteCategory(@PathVariable UUID id) {
        ctService.deleteCategory(id);
    }

    @PutMapping("/category/update")
    public category updateCategory(@RequestParam UUID id,
                                   @RequestBody category requestCategory) {
        category cat = ctService.getCategoryById(id);
        cat.setName(requestCategory.getName());
        return ctService.updateCategory(cat);
    }
}
