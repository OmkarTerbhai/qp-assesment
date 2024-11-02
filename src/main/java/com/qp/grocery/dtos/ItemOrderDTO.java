package com.qp.grocery.dtos;

import com.qp.grocery.entities.GroceryItem;
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
