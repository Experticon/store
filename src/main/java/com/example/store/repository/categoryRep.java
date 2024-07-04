package com.example.store.repository;

import com.example.store.model.category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface categoryRep extends JpaRepository<category, UUID> {
}
