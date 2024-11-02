package com.qp.grocery.repositories;

import com.qp.grocery.entities.GroceryItem;
import com.qp.grocery.dtos.GroceryItemDTO;
import com.qp.grocery.dtos.UpdateGroceryItemDTO;
import com.qp.grocery.dtos.UpdateInventoryCountDTO;
import com.qp.grocery.exceptions.InvalidPayloadException;
import com.qp.grocery.exceptions.ItemNotFoundException;

import java.util.List;

public interface GroceryStoreDAO {

    GroceryItem createGroceryItem(GroceryItemDTO dto);

    GroceryItem getGroceryItem(long id) throws ItemNotFoundException;

    List<GroceryItem> getGroceryItems(String name, Double price, String category, int page, int size);

    GroceryItem updateGroceryItem(Long id, UpdateGroceryItemDTO dto) throws ItemNotFoundException;

    GroceryItem updateGroceryItem(Long id, GroceryItemDTO dto) throws ItemNotFoundException;

    GroceryItem deleteGroceryItem(Long id) throws ItemNotFoundException;

    GroceryItem updateInventoryCount(UpdateInventoryCountDTO dto) throws ItemNotFoundException, InvalidPayloadException;
}
