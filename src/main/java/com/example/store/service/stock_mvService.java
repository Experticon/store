package com.example.store.service;

import com.example.store.model.product;
import com.example.store.model.stock_mv;
import com.example.store.repository.productRep;
import com.example.store.repository.stock_mvRep;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class stock_mvService {

    @PersistenceContext
    private EntityManager entityManager;

    private final productRep pRep;
    private final stock_mvRep stRep;


    public List<stock_mv> getAllStock_mv() {
        return stRep.findAll();
    }


    public stock_mv getStock_mvById(UUID id) {
        return stRep.findById(id).orElse(null);
    }

    /**
     *По типу поставки на склад меняется итоговое количество товара
     * тоже самое работает и при обновлении склада
     */
    @Transactional
    public stock_mv createStock_mv(stock_mv st) {
        product product = st.getProduct();
        int newQuantity = product.getQuantity();

        if ("IN".equals(st.getMovementType())) {
            newQuantity += st.getQuantity();
        } else if ("OUT".equals(st.getMovementType())) {
            if (newQuantity < st.getQuantity()) {
                throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
            }
            newQuantity -= st.getQuantity();
        } else {
            throw new IllegalArgumentException("Invalid movement type: " + st.getMovementType());
        }

        product.setQuantity(newQuantity);
        product.setLastQuantityUpdate(st.getMovementDate());
        pRep.save(product);

        return stRep.save(st);
    }


    public stock_mv updateStock_mv(stock_mv updatedStockMv) {
        stock_mv existingStockMv = stRep.findById(updatedStockMv.getId())
                .orElseThrow(() -> new IllegalArgumentException("Stock movement not found: " + updatedStockMv.getId()));

        product product = updatedStockMv.getProduct();
        int newProductQuantity = getNewProductQuantity(updatedStockMv, product, existingStockMv);

        if ("OUT".equals(existingStockMv.getMovementType()) && newProductQuantity < 0) {
            throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
        }

        product.setQuantity(newProductQuantity);
        product.setLastQuantityUpdate(updatedStockMv.getMovementDate());
        pRep.save(product);

        // Сохраняем обновленную запись stock_mv
        return stRep.save(updatedStockMv);
    }

    private int getNewProductQuantity(stock_mv updatedStockMv, product product, stock_mv existingStockMv) {
        int currentProductQuantity = product.getQuantity();
        int existingStockQuantity = existingStockMv.getQuantity();
        int updatedStockQuantity = updatedStockMv.getQuantity();

        // Вычитаем количество существующей записи
        if ("IN".equals(existingStockMv.getMovementType())) {
            currentProductQuantity -= existingStockQuantity;
        } else if ("OUT".equals(existingStockMv.getMovementType())) {
            currentProductQuantity += existingStockQuantity;
        }

        // Добавляем количество новой записи
        if ("IN".equals(updatedStockMv.getMovementType())) {
            currentProductQuantity += updatedStockQuantity;
        } else if ("OUT".equals(updatedStockMv.getMovementType())) {
            if (currentProductQuantity < updatedStockQuantity) {
                throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
            }
            currentProductQuantity -= updatedStockQuantity;
        }

        return currentProductQuantity;
    }


    public void deleteStock_mv(UUID id) {
        stRep.deleteById(id);
    }
}
