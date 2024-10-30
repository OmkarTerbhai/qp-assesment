package com.qp.grocery.controllers;

import com.qp.grocery.entities.GroceryItemDTO;
import com.qp.grocery.entities.UpdateGroceryItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/grocery")
public class GroceryController implements IGroceryStoreController {
    @Override
    @PostMapping("/")
    public ResponseEntity<?> createGroceryItem(@RequestBody GroceryItemDTO groceryItem) {
        return null;
    }

    @Override
    public ResponseEntity<?> getGroceryItem(@RequestParam long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> listGroceryItems(@RequestParam(required = false) String name,
                                              @RequestParam(required = false) double price,
                                              @RequestParam(required = false) String category,
                                              @RequestParam int page,
                                              @RequestParam int size) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateGroceryItem(int id, UpdateGroceryItemDTO updateGroceryItem) {
        return null;
    }
}
