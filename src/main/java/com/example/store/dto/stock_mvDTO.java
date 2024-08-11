package com.example.store.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @class stock_mvDTO
 * Data transfer object для @class stock_mv
 */
@Data
public class stock_mvDTO {

    private UUID id;
    private UUID product_id;
    private int quantity;
    private String movementType;
    private LocalDateTime movementDate;
}
