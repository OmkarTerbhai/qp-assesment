package com.qp.grocery.controllers;

import com.qp.grocery.entities.GroceryItemDTO;
import com.qp.grocery.entities.UpdateGroceryItemDTO;
import org.springframework.http.ResponseEntity;

public interface IGroceryStoreController {
    ResponseEntity<?> createGroceryItem(GroceryItemDTO groceryItem);

    ResponseEntity<?> getGroceryItem(long id);

    ResponseEntity<?> listGroceryItems(String name, double price, String category, int page, int size);

    ResponseEntity<?> updateGroceryItem(int id, UpdateGroceryItemDTO updateGroceryItem);
}
