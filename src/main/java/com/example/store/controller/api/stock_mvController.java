package com.example.store.controller.api;

import com.example.store.dto.stock_mvDTO;
import com.example.store.interfaces.apiInterfaceCRUD;
import com.example.store.model.product;
import com.example.store.model.stock_mv;
import com.example.store.service.productService;
import com.example.store.service.stock_mvService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @class stock_mvController
 * Каждый контролер написан под каждую модель отдельно.
 * Часть api, отвечающий за взаимодействие
 * с моделью "stock_mv". Отправляет и принимает json файлы.
 */
@RestController
@RequestMapping("/api/stock_mv")
public class stock_mvController implements apiInterfaceCRUD<stock_mvDTO> {

    private final productService pService;
    private final stock_mvService stService;

    public stock_mvController(productService pService, stock_mvService stService) {
        this.pService = pService;
        this.stService = stService;
    }

    // Метод для преобразования сущности в DTO
    private stock_mvDTO convertToDTO(stock_mv stockMovement) {
        stock_mvDTO dto = new stock_mvDTO();
        dto.setId(stockMovement.getId());
        dto.setProduct_id(stockMovement.getProduct().getId());
        dto.setQuantity(stockMovement.getQuantity());
        dto.setMovementType(stockMovement.getMovementType());
        dto.setMovementDate(stockMovement.getMovementDate());
        return dto;
    }

    // Метод для преобразования DTO в сущность
    private stock_mv convertToEntity(stock_mvDTO dto) {
        stock_mv stockMovement = new stock_mv();
        stockMovement.setId(dto.getId());
        stockMovement.setProduct(pService.getProductById(dto.getProduct_id()));
        stockMovement.setQuantity(dto.getQuantity());
        stockMovement.setMovementType(dto.getMovementType());
        stockMovement.setMovementDate(dto.getMovementDate());
        return stockMovement;
    }

    @GetMapping("")
    @ResponseBody
    public List<stock_mvDTO> getAllEntities() {
        return stService.getAllStock_mv().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public stock_mvDTO getEntityById(@PathVariable UUID id) {
        stock_mv stockMovement = stService.getStock_mvById(id);
        return convertToDTO(stockMovement);
    }

    @PostMapping("/stock/create")
    @ResponseBody
    public stock_mvDTO createEntity(@RequestBody stock_mvDTO newStockMovementDTO) {
        stock_mv newStockMovement = convertToEntity(newStockMovementDTO);
        stock_mv createdStockMovement = stService.createStock_mv(newStockMovement);
        return convertToDTO(createdStockMovement);
    }

    @DeleteMapping("/stock/delete/{id}")
    public void deleteEntity(@PathVariable UUID id) {
        stService.deleteStock_mv(id);
    }

    @PutMapping("/stock/update")
    @ResponseBody
    public stock_mvDTO updateEntity(@RequestParam UUID id, @RequestBody stock_mvDTO updatedStockMovementDTO) {
        stock_mv updatedStockMovement = convertToEntity(updatedStockMovementDTO);
        updatedStockMovement.setId(id);
        stock_mv updatedStock = stService.updateStock_mv(updatedStockMovement);
        return convertToDTO(updatedStock);
    }
}

