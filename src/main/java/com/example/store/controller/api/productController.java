package com.example.store.controller.api;

import com.example.store.dto.productDTO;
import com.example.store.interfaces.apiInterfaceCRUD;
import com.example.store.model.product;
import com.example.store.service.categoryService;
import com.example.store.service.productService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @class productController
 * Часть api, отвечающий за взаимодействие
 * с моделью "product". Отправляет и принимает json файлы.
 */
@RestController
@RequestMapping("/api/products")
public class productController implements apiInterfaceCRUD<productDTO> {

    private final categoryService ctService;
    private final productService pService;

    public productController(categoryService ctService, productService pService) {
        this.ctService = ctService;
        this.pService = pService;
    }

    @GetMapping("")
    @ResponseBody
    public List<productDTO> getAllEntities() {
        List<product> products = pService.getAllProducts();
        return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public productDTO getEntityById(@PathVariable UUID id) {
        product prod = pService.getProductById(id);
        return convertToDTO(prod);
    }

    @PostMapping("/product/create")
    public productDTO createEntity(@RequestBody productDTO newProductDTO) {
        product newProduct = convertToEntity(newProductDTO);
        product createdProduct = pService.createProduct(newProduct);
        return convertToDTO(createdProduct);
    }

    @DeleteMapping("/product/delete/{id}")
    public void deleteEntity(@PathVariable UUID id) {
        pService.deleteProduct(id);
    }

    @PutMapping("/product/update")
    public productDTO updateEntity(@RequestParam UUID id, @RequestBody productDTO updatedProductDTO) {
        product prod = pService.getProductById(id);
        product updatedProduct = convertToEntity(updatedProductDTO);
        updatedProduct.setId(id);
        if (updatedProduct.getLastQuantityUpdate() == null) {
            updatedProduct.setLastQuantityUpdate(prod.getLastQuantityUpdate());
        }
        if (updatedProduct.getCreatedAt() == null) {
            updatedProduct.setCreatedAt(prod.getCreatedAt());
        }

        product updatedProd = pService.updateProduct(updatedProduct);
        return convertToDTO(updatedProd);
    }

    private productDTO convertToDTO(product prod) {
        productDTO prodDTO = new productDTO();
        prodDTO.setId(prod.getId());
        prodDTO.setName(prod.getName());
        prodDTO.setSku(prod.getSku());
        prodDTO.setDescription(prod.getDescription());
        prodDTO.setCategory_id(prod.getCategory().getId());
        prodDTO.setPrice(prod.getPrice());
        prodDTO.setQuantity(prod.getQuantity());
        prodDTO.setLastQuantityUpdate(prod.getLastQuantityUpdate());
        prodDTO.setCreatedAt(prod.getCreatedAt());

        // Вычисляем real_price - разница между price и её 10%. Не сохраняется в базу данных.
        BigDecimal realPrice = prod.getPrice().multiply(BigDecimal.valueOf(0.9));
        prodDTO.setReal_price(realPrice);

        return prodDTO;
    }

    // Преобразование productDTO в product
    private product convertToEntity(productDTO prodDTO) {
        product prod = new product();
        prod.setId(prodDTO.getId());
        prod.setName(prodDTO.getName());
        prod.setSku(prodDTO.getSku());
        prod.setDescription(prodDTO.getDescription());
        prod.setCategory(ctService.getCategoryById(prodDTO.getCategory_id()));
        prod.setPrice(prodDTO.getPrice());
        prod.setQuantity(prodDTO.getQuantity());
        prod.setLastQuantityUpdate(prodDTO.getLastQuantityUpdate());
        prod.setCreatedAt(prodDTO.getCreatedAt());

        return prod;
    }
}