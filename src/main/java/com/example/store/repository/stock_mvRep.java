package com.example.store.repository;

import com.example.store.model.stock_mv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface stock_mvRep extends JpaRepository<stock_mv, UUID> {
}
