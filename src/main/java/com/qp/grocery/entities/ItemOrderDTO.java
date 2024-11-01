package com.qp.grocery.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemOrderDTO {
    private Long itemId;
    private int count;

    public ItemOrderDTO(GroceryItem item) {
        this.itemId = item.getId();
    }
}
