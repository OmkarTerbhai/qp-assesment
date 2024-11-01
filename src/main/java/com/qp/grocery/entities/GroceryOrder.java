package com.qp.grocery.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroceryOrder extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal orderPrice;

    @OneToMany(mappedBy = "groceryOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GroceryItem> items;

    @PrePersist
    void persist() {
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }

    @PreUpdate
    void update() {
        this.setUpdatedAt(LocalDateTime.now());
    }

}
