package com.qp.grocery.controllers;

import com.qp.grocery.dtos.GroceryItemDTO;
import com.qp.grocery.dtos.UpdateGroceryItemDTO;
import com.qp.grocery.dtos.UpdateInventoryCountDTO;
import com.qp.grocery.exceptions.InvalidPayloadException;
import com.qp.grocery.exceptions.ItemNotFoundException;
import com.qp.grocery.utils.ResponseData;
import org.springframework.http.ResponseEntity;

public interface GroceryStoreController {
    ResponseData<GroceryItemDTO> createGroceryItem(GroceryItemDTO groceryItem) throws InvalidPayloadException;

    ResponseData<GroceryItemDTO> getGroceryItem(long id) throws ItemNotFoundException;

    ResponseEntity<?> listGroceryItems(String name, Double price, String category, int page, int size);

    ResponseEntity<?> updateGroceryItem(Long id, UpdateGroceryItemDTO updateGroceryItem) throws ItemNotFoundException;

    ResponseEntity<?> updateGroceryItem(Long id, GroceryItemDTO updateGroceryItem) throws InvalidPayloadException, ItemNotFoundException;

    ResponseEntity<?> deleteGroceryItem(Long id) throws ItemNotFoundException;

    ResponseEntity<?> updateInventory(UpdateInventoryCountDTO updateGroceryItem) throws InvalidPayloadException, ItemNotFoundException;

}
