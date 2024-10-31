package com.qp.grocery.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GroceryCategoryEnum {
    EATABLE(1, "Eatable"),
    DRINK(2, "Drink"),
    SPICE(3, "Spice"),
    CONDIMENT(4, "Condiment");

    private final int id;
    private final String value;

    public static String getCategoryValue(String category) {
        for(GroceryCategoryEnum g : GroceryCategoryEnum.values()) {
            if(g.value.equalsIgnoreCase(category)) {
                return g.getValue();
            }
        }
        return null;
    }

    public static GroceryCategoryEnum getCategory(String category) {
        for(GroceryCategoryEnum g : GroceryCategoryEnum.values()) {
            if(g.value.equalsIgnoreCase(category)) {
                return g;
            }
        }
        return null;
    }

}
