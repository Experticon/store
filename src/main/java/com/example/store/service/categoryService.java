package com.example.store.service;

import com.example.store.model.category;
import com.example.store.repository.categoryRep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class categoryService {

    private final categoryRep ctRep;


    public List<category> getAllCategories() {
        return ctRep.findAll();
    }


    public category getCategoryById(UUID id) {
        return ctRep.findById(id).orElse(null);
    }
    public category getCategoryByName(String name) {
        return ctRep.findByName(name).orElse(null);
    }

    public category createCategory(category ct) {
        return ctRep.save(ct);
    }


    public category updateCategory(category ct) {
        return ctRep.save(ct);
    }


    public void deleteCategory(UUID id) {
        ctRep.deleteById(id);
    }
}
