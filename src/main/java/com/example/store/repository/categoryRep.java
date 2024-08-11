package com.example.store.repository;

import com.example.store.model.category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface categoryRep extends JpaRepository<category, UUID> {

    Optional<category> findByName(String name);
}
