package com.qp.grocery.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.GroceryCategoryEnum;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroceryItem extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GroceryCategoryEnum category;

    @Column(nullable = false)
    private Integer inventoryCount;
}
