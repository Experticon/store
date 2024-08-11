package com.example.store.service;

import com.example.store.model.product;
import com.example.store.model.stock_mv;
import com.example.store.repository.productRep;
import com.example.store.repository.stock_mvRep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class productService {
    private final productRep pRep;
    private final stock_mvRep stRep;


    public List<product> getAllProducts() {
        return pRep.findAll();
    }


    public product getProductById(UUID id) {
        return pRep.findById(id).orElse(null);
    }

    /**
     * @method createProduct
     * Важно уточнить здесь создание поставки вместе с продуктом.
     * При тестировании в Postman нельзя отловить данную поставку, поэтому
     * её придётся либо удалять, либо создавать продукт без начального количества
     */
    public product createProduct(product p) {
        product savedProduct = pRep.save(p);

        if (p.getQuantity() > 0) {
            stock_mv newStockMovement = new stock_mv();
            newStockMovement.setProduct(savedProduct);
            newStockMovement.setQuantity(savedProduct.getQuantity());
            newStockMovement.setMovementType("IN");
            newStockMovement.setMovementDate(savedProduct.getCreatedAt());

            stRep.save(newStockMovement);
        }

        return savedProduct;
    }


    public product updateProduct(product p) {
        return pRep.save(p);
    }


    public void deleteProduct(UUID id) {
        pRep.deleteById(id);
    }
}
