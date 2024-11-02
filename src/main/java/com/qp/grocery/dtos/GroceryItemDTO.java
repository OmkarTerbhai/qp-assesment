package com.qp.grocery.dtos;

import com.qp.grocery.entities.GroceryItem;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroceryItemDTO {

    long id;

    private String name;

    private BigDecimal price;

    private String description;

    private String category;

    private Integer inventoryCount;

    public GroceryItemDTO(GroceryItem item) {
        this.id = item.getId();
        this.name = item.getName();
        this.category = item.getCategory().getValue();
        this.price = item.getPrice();
        this.description = item.getDescription();
        this.inventoryCount = item.getInventoryCount();
    }


}
