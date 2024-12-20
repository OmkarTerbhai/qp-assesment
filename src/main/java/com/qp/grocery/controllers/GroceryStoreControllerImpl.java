package com.qp.grocery.controllers;

import com.qp.grocery.dtos.GroceryItemDTO;
import com.qp.grocery.dtos.UpdateGroceryItemDTO;
import com.qp.grocery.dtos.UpdateInventoryCountDTO;
import com.qp.grocery.exceptions.InvalidPayloadException;
import com.qp.grocery.exceptions.ItemNotFoundException;
import com.qp.grocery.services.GroceryService;
import com.qp.grocery.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/grocery")
public class GroceryStoreControllerImpl implements GroceryStoreController {

    @Autowired
    private GroceryService groceryService;

    @Override
    @PostMapping
    public ResponseData<GroceryItemDTO> createGroceryItem(@RequestBody GroceryItemDTO groceryItem) throws InvalidPayloadException {
        return this.groceryService.createGroceryItem(groceryItem);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseData<GroceryItemDTO> getGroceryItem(@PathVariable long id) throws ItemNotFoundException {
        return this.groceryService.getGroceryItem(id);
    }

    @Override
    @GetMapping("/filter")
    public ResponseEntity<ResponseData<List<GroceryItemDTO>>> listGroceryItems(@RequestParam(required = false) String name,
                                                                               @RequestParam(required = false) Double price,
                                                                               @RequestParam(required = false) String category,
                                                                               @RequestParam int page,
                                                                               @RequestParam int size) {
        ResponseData<List<GroceryItemDTO>> apiRes = groceryService.getGroceryItems(name, price, category, page, size);
        ResponseEntity<ResponseData<List<GroceryItemDTO>>> response = new ResponseEntity<>(apiRes, HttpStatus.OK);

        return response;
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<ResponseData<GroceryItemDTO>> updateGroceryItem(@RequestParam Long id, @RequestBody UpdateGroceryItemDTO updateGroceryItem) throws ItemNotFoundException {
        ResponseData<GroceryItemDTO> apiRes = this.groceryService.updateGroceryItem(id, updateGroceryItem);

        ResponseEntity<ResponseData<GroceryItemDTO>> res = new ResponseEntity<>(apiRes, HttpStatus.CREATED);

        return res;
    }

    @Override
    @PatchMapping("/")
    public ResponseEntity<ResponseData<GroceryItemDTO>> updateGroceryItem(@RequestParam Long id, @RequestBody GroceryItemDTO dto) throws InvalidPayloadException, ItemNotFoundException {
        ResponseData<GroceryItemDTO> apiRes = this.groceryService.updateGroceryItem(id, dto);

        return new ResponseEntity<ResponseData<GroceryItemDTO>>(apiRes, HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ResponseData<GroceryItemDTO>> deleteGroceryItem(@RequestParam Long id) throws ItemNotFoundException {
        ResponseData<GroceryItemDTO> apiRes = groceryService.deleteGroceryItem(id);

        return new ResponseEntity<ResponseData<GroceryItemDTO>>(apiRes, HttpStatus.OK);
    }

    @Override
    @PutMapping("/inventory")
    public ResponseEntity<?> updateInventory(@RequestBody UpdateInventoryCountDTO updateGroceryItem) throws InvalidPayloadException, ItemNotFoundException {
        ResponseData<GroceryItemDTO> apiRes = groceryService.updateInventoryCount(updateGroceryItem);

        return new ResponseEntity<>(apiRes, HttpStatus.CREATED);
    }
}
