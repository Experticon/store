package com.example.store.service;

import com.example.store.model.product;
import com.example.store.repository.productRep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class productService {
    private final productRep pRep;


    public List<product> getAllProducts() {
        return pRep.findAll();
    }


    public product getProductById(UUID id) {
        return pRep.findById(id).orElse(null);
    }


    public product createProduct(product p) {
        return pRep.save(p);
    }


    public product updateProduct(product p) {
        return pRep.save(p);
    }


    public void deleteProduct(UUID id) {
        pRep.deleteById(id);
    }
}
