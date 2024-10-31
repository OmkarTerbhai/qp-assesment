package com.qp.grocery.controllers;

import com.qp.grocery.entities.GroceryItemDTO;
import com.qp.grocery.entities.UpdateGroceryItemDTO;
import com.qp.grocery.exceptions.InvalidPayloadException;
import com.qp.grocery.exceptions.ItemNotFoundException;
import com.qp.grocery.utils.ResponseData;
import org.springframework.http.ResponseEntity;

public interface IGroceryStoreController {
    ResponseData<GroceryItemDTO> createGroceryItem(GroceryItemDTO groceryItem) throws InvalidPayloadException;

    ResponseData<GroceryItemDTO> getGroceryItem(long id) throws ItemNotFoundException;

    ResponseEntity<?> listGroceryItems(String name, Double price, String category, int page, int size);

    ResponseEntity<?> updateGroceryItem(Long id, UpdateGroceryItemDTO updateGroceryItem);
}
