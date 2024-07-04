package com.example.store.repository;

import com.example.store.model.category;
import com.example.store.model.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface productRep extends JpaRepository<product, UUID> {
    List<product> findByCategory(category category);
}
