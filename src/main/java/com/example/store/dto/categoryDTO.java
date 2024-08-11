package com.example.store.dto;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * @class categoryDTO
 * Data transfer object для @class category
 * Используется @Builder
 */
@Data
@Builder
public class categoryDTO {
    private UUID id;
    private String name;
}
