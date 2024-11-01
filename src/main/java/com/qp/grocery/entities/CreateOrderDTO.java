package com.qp.grocery.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDTO {

    Long id;

    List<ItemOrderDTO> items;

    BigDecimal price;

    public CreateOrderDTO(GroceryOrder groceryOrder) {
        this.id = groceryOrder.getId();
        this.price = groceryOrder.getOrderPrice();
        this.items = new ArrayList<>();

        for(GroceryItem item : groceryOrder.getItems()) {
            items.add(new ItemOrderDTO(item));
        }
    }
}
