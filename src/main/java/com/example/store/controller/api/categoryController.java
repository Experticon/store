package com.example.store.controller.api;

import com.example.store.dto.categoryDTO;
import com.example.store.interfaces.apiInterfaceCRUD;
import com.example.store.model.category;
import com.example.store.service.categoryService;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @class category
 * Часть api, отвечающий за взаимодействие
 * с моделью "category". Отправляет и принимает json файлы.
 */
@RestController
@RequestMapping("/api/categories")
public class categoryController implements apiInterfaceCRUD<categoryDTO>{

    private final categoryService ctService;

    public categoryController(categoryService ctService) {
        this.ctService = ctService;
    }

    @GetMapping("")
    @ResponseBody
    public List<categoryDTO> getAllEntities() {
        return ctService.getAllCategories().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public categoryDTO getEntityById(@PathVariable UUID id) {
        category cat = ctService.getCategoryById(id);
        return convertToDTO(cat);
    }

    @PostMapping("/category/create")
    public categoryDTO createEntity(@RequestBody categoryDTO requestCategoryDTO) {
        category newCategory = convertToEntity(requestCategoryDTO);
        category createdCategory = ctService.createCategory(newCategory);
        return convertToDTO(createdCategory);
    }

    @DeleteMapping("/category/delete/{id}")
    public void deleteEntity(@PathVariable UUID id) {
        ctService.deleteCategory(id);
    }

    @PutMapping("/category/update")
    public categoryDTO updateEntity(@RequestParam UUID id,
                                    @RequestBody categoryDTO requestCategoryDTO) {
        category updatedCategory = convertToEntity(requestCategoryDTO);
        updatedCategory.setId(id);
        updatedCategory = ctService.updateCategory(updatedCategory);
        return convertToDTO(updatedCategory);
    }

    @GetMapping("/name/{name}")
    @ResponseBody
    public categoryDTO getCategoryByName(@PathVariable String name) {
        category cat = ctService.getCategoryByName(name);
        return convertToDTO(cat);
    }

    // Метод для конвертации из category в categoryDTO
    private categoryDTO convertToDTO(category cat) {
        return categoryDTO.builder()
                .id(cat.getId())
                .name(cat.getName())
                .build();
    }

    // Метод для конвертации из categoryDTO в category
    private category convertToEntity(categoryDTO catDTO) {
        category cat = new category();
        cat.setName(catDTO.getName());
        return cat;
    }
}

