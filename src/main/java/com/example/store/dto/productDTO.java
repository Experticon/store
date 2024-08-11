package com.example.store.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @class productDTO
 * Data transfer object для @class product
 * Имеет дополнительное поле - real_price.
 * Это разница между price и 10% price.
 */
@Data
public class productDTO {
    private UUID id;
    private String name;
    private String sku;
    private String description;
    private UUID category_id;
    private BigDecimal price;
    private BigDecimal real_price;
    private int quantity;
    private LocalDateTime lastQuantityUpdate;
    private LocalDateTime createdAt;
}
