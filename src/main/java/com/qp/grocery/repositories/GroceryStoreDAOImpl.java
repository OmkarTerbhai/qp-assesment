package com.qp.grocery.repositories;

import com.qp.grocery.entities.GroceryItem;
import com.qp.grocery.dtos.GroceryItemDTO;
import com.qp.grocery.dtos.UpdateGroceryItemDTO;
import com.qp.grocery.dtos.UpdateInventoryCountDTO;
import com.qp.grocery.exceptions.InvalidPayloadException;
import com.qp.grocery.exceptions.ItemNotFoundException;
import com.qp.grocery.utils.GroceryCategoryEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class GroceryStoreDAOImpl implements GroceryStoreDAO {

    @Autowired
    private GroceryRepository groceryRepository;
    @Override
    public GroceryItem createGroceryItem(GroceryItemDTO dto) {
        GroceryItem item = new GroceryItem();
        item.setName(dto.getName());
        item.setCategory(GroceryCategoryEnum.getCategory(dto.getCategory()));
        item.setPrice(dto.getPrice());
        item.setInventoryCount(dto.getInventoryCount());
        item.setDescription(dto.getDescription());

        return this.groceryRepository.save(item);
    }


    @Override
    public GroceryItem getGroceryItem(long id) throws ItemNotFoundException {
        return this.groceryRepository.findById(id).
                orElseThrow((() -> new ItemNotFoundException("Could not find grocery item with id : " + id)));
    }

    @Override
    public List<GroceryItem> getGroceryItems(String name, Double price, String category, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        name = StringUtils.isBlank(name) ? null : name;
        category = StringUtils.isBlank(category) ? null : category;

        Page<GroceryItem> itemList = this.groceryRepository.getItems(name, category, price, pageable);

        return itemList.stream().toList();
    }

    @Override
    public GroceryItem updateGroceryItem(Long id, UpdateGroceryItemDTO dto) throws ItemNotFoundException {
        GroceryItem item = this.groceryRepository.findById(id).
                orElseThrow(() -> new ItemNotFoundException("Could not find item with id " + id));

        item.setName(dto.getName());
        item.setPrice(BigDecimal.valueOf(dto.getPrice()));
        groceryRepository.save(item);
        return item;
    }

    @Override
    public GroceryItem updateGroceryItem(Long id, GroceryItemDTO dto) throws ItemNotFoundException {
        GroceryItem item = this.groceryRepository.findById(id).
                orElseThrow(() -> new ItemNotFoundException("Could not find item with id " + id));

        getUpdatedGroceryItemEntity(item, dto);
        groceryRepository.save(item);

        return item;
    }

    @Override
    public GroceryItem deleteGroceryItem(Long id) throws ItemNotFoundException {
        GroceryItem item = groceryRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Could not find item with id : " + id));

        groceryRepository.delete(item);
        return item;
    }

    @Override
    public GroceryItem updateInventoryCount(UpdateInventoryCountDTO dto) throws ItemNotFoundException, InvalidPayloadException {
        GroceryItem item = this.groceryRepository.findById(dto.getId())
                .orElseThrow(() -> new ItemNotFoundException("Could not find item with id " + dto.getId()));

        if(dto.isIncr())
            item.setInventoryCount(item.getInventoryCount() + dto.getUpdateCount());
        else {
            int updatedCnt = item.getInventoryCount() - dto.getUpdateCount();
            if(updatedCnt < 0) {
                throw new InvalidPayloadException("Cannot decrease inventory count to less than zero");
            }
            if(updatedCnt == 0) {
                throw new InvalidPayloadException("Cannot update inventory count to zero, please explicitly delete the item");
            }
            item.setInventoryCount(updatedCnt);
        }

        groceryRepository.save(item);

        return item;
    }

    private GroceryItem getUpdatedGroceryItemEntity(GroceryItem item, GroceryItemDTO dto) {
        item.setName(dto.getName());
        item.setCategory(GroceryCategoryEnum.getCategory(dto.getCategory()));
        item.setPrice(dto.getPrice());
        item.setInventoryCount(dto.getInventoryCount());
        item.setDescription(dto.getDescription());

        return item;
    }
}
