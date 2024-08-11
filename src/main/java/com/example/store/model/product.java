package com.example.store.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * @class products
 * Модель "products" - товар
 * Имеет такие атрибуты, как:
 * id (UUID),
 * название,
 * артикул (уникален для каждого),
 * описание,
 * категория (вторичный ключ),
 * цена,
 * количество товаров,
 * последнее обновление товара,
 * создание товара.
 */

@Setter
@Getter
@Entity
@Table(name = "products")
public class product {

    public product() {
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    @NotBlank(message = "Name can't be empty or blank")
    private String name;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "SKU can't be empty or blank")
    private String sku;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private category category;

    @Column(nullable = false)
    @Min(value=1, message = "Minimum price is 1")
    private BigDecimal price;

    @Column(nullable = false)
    @Min(value=0, message = "Minimum quantity is 0")
    private int quantity;

    @Column(name = "last_quantity_update", nullable = false)
    private LocalDateTime lastQuantityUpdate;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Этого нет в базе данных, но нам нужно знать набор поставок именно для этой сущности
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<stock_mv> stockMovements;


}