package com.example.store.controller;

import com.example.store.dto.categoryDTO;
import com.example.store.dto.productDTO;
import com.example.store.dto.stock_mvDTO;
import com.example.store.model.category;
import com.example.store.service.categoryService;
import com.example.store.model.product;
import com.example.store.service.productService;
import com.example.store.model.stock_mv;
import com.example.store.service.stock_mvService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        List<categoryDTO> categories = ctService.getAllCategories().stream()
                .map(this::convertToCategoryDTO)
                .toList();
        List<productDTO> products = pService.getAllProducts().stream()
                .map(this::convertToProductDTO)
                .toList();
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        return "createElem";
    }

    @GetMapping("/category/edit/{id}")
    public String editCategory(@PathVariable UUID id, Model model) {
        categoryDTO catDTO = convertToCategoryDTO(ctService.getCategoryById(id));
        model.addAttribute("category", catDTO);
        return "edit_category";
    }

    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable UUID id, Model model) {
        productDTO prodDTO = convertToProductDTO(pService.getProductById(id));
        List<categoryDTO> categories = ctService.getAllCategories().stream()
                .map(this::convertToCategoryDTO)
                .toList();
        model.addAttribute("product", prodDTO);
        model.addAttribute("categories", categories);
        return "edit_product";
    }

    @GetMapping("/stock_mv/edit/{id}")
    public String editStock_mv(@PathVariable UUID id, Model model) {
        stock_mvDTO stockMvDTO = convertToStockMvDTO(stService.getStock_mvById(id));
        List<productDTO> products = pService.getAllProducts().stream()
                .map(this::convertToProductDTO)
                .toList();

        // Форматируем дату и время
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String formattedMovementDate = stockMvDTO.getMovementDate().format(formatter);

        // Добавляем отформатированную дату в модель
        model.addAttribute("stock_mv", stockMvDTO);
        model.addAttribute("products", products);
        model.addAttribute("formattedMovementDate", formattedMovementDate);

        return "edit_stock_mv";
    }

    // Преобразование category в categoryDTO
    private categoryDTO convertToCategoryDTO(category cat) {
        return categoryDTO.builder()
                .id(cat.getId())
                .name(cat.getName())
                .build();
    }

    // Преобразование product в productDTO
    private productDTO convertToProductDTO(product prod) {
        productDTO dto = new productDTO();
        dto.setId(prod.getId());
        dto.setName(prod.getName());
        dto.setSku(prod.getSku());
        dto.setDescription(prod.getDescription());
        dto.setCategory_id(prod.getCategory().getId());
        dto.setPrice(prod.getPrice());
        dto.setReal_price(null);
        dto.setQuantity(prod.getQuantity());
        dto.setLastQuantityUpdate(prod.getLastQuantityUpdate());
        dto.setCreatedAt(prod.getCreatedAt());
        return dto;
    }

    // Преобразование stock_mv в stock_mvDTO
    private stock_mvDTO convertToStockMvDTO(stock_mv stockMovement) {
        stock_mvDTO dto = new stock_mvDTO();
        dto.setId(stockMovement.getId());
        dto.setProduct_id(stockMovement.getProduct().getId());
        dto.setQuantity(stockMovement.getQuantity());
        dto.setMovementType(stockMovement.getMovementType());
        dto.setMovementDate(stockMovement.getMovementDate());
        return dto;
    }
}
