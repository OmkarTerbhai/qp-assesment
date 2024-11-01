package com.qp.grocery.services;

import com.qp.grocery.entities.GroceryItemDTO;
import com.qp.grocery.entities.UpdateGroceryItemDTO;
import com.qp.grocery.entities.UpdateInventoryCountDTO;
import com.qp.grocery.exceptions.InvalidPayloadException;
import com.qp.grocery.exceptions.ItemNotFoundException;
import com.qp.grocery.utils.ResponseData;

import java.util.List;

public interface IGroceryService {

    ResponseData<GroceryItemDTO> createGroceryItem(GroceryItemDTO dto) throws InvalidPayloadException;

    ResponseData<GroceryItemDTO> getGroceryItem(long id) throws ItemNotFoundException;

    ResponseData<List<GroceryItemDTO>> getGroceryItems(String name, Double price, String category, int page, int size);

    ResponseData<GroceryItemDTO> updateGroceryItem(Long id, UpdateGroceryItemDTO dto) throws ItemNotFoundException;

    ResponseData<GroceryItemDTO> updateGroceryItem(Long id, GroceryItemDTO dto) throws InvalidPayloadException, ItemNotFoundException;

    ResponseData<GroceryItemDTO> deleteGroceryItem(Long id) throws ItemNotFoundException;

    ResponseData<GroceryItemDTO> updateInventoryCount(UpdateInventoryCountDTO dto) throws InvalidPayloadException, ItemNotFoundException;
}
