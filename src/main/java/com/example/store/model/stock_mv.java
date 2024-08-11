package com.example.store.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;
/**
 * @class stock_mv
 * Модель "stock_movements" - поставки товара на склад
 * Имеет такие атрибуты, как:
 * id (UUID),
 * поставляемый товар,
 * количество,
 * тип (поставка товара или выгрузка товара),
 * дата
 */
@Setter
@Getter
@Entity
@Table(name = "stock_movements")
public class stock_mv {

    public stock_mv() {
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private product product;

    @Column(nullable = false)
    @Min(value=1, message = "Minimum quantity is 1")
    private int quantity;

    @Column(name = "movement_type", nullable = false)
    @NotBlank(message = "Movement type can't be empty or blank")
    private String movementType;

    @Column(name = "movement_date", nullable = false)
    private LocalDateTime movementDate;


}