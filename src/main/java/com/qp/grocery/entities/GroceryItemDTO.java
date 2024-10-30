package com.qp.grocery.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import utils.GroceryCategoryEnum;

import java.math.BigDecimal;

public class GroceryItemDTO {

    private String name;

    private BigDecimal price;

    private String description;

    private String category;

    private Integer inventoryCount;
}
