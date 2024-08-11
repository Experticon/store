package com.example.store.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 * @class category
 * Модель "categories" - категории товара
 * Имеет id (UUID) и название
 */
@Getter
@Setter
@Entity
@Table(name = "categories")
public class category {

    public category() {
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Name can't be empty or blank")
    private String name;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private Set<product> products;


}
