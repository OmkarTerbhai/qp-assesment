package com.qp.grocery.services;

import com.qp.grocery.entities.GroceryItem;
import com.qp.grocery.entities.GroceryItemDTO;
import com.qp.grocery.entities.UpdateGroceryItemDTO;
import com.qp.grocery.exceptions.InvalidPayloadException;
import com.qp.grocery.exceptions.ItemNotFoundException;
import com.qp.grocery.repositories.IGroceryStoreDAO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.qp.grocery.utils.GroceryCategoryEnum;
import com.qp.grocery.utils.ResponseData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GroceryStoreService implements IGroceryService {

    @Autowired
    private IGroceryStoreDAO groceryStoreRepository;
    @Override
    public ResponseData<GroceryItemDTO> createGroceryItem(GroceryItemDTO dto) throws InvalidPayloadException {

            validateGroceryItem(dto);
            populateGroceryItem(dto);
            GroceryItem createdGroceryItem = groceryStoreRepository.createGroceryItem(dto);

            GroceryItemDTO groceryItemDTO = new GroceryItemDTO(createdGroceryItem);
            ResponseData<GroceryItemDTO> res = new ResponseData(groceryItemDTO, true, "Created Grocery time", null);
            return res;

    }


    @Override
    public ResponseData<GroceryItemDTO> getGroceryItem(long id) throws ItemNotFoundException {

            GroceryItem item = groceryStoreRepository.getGroceryItem(id);
            GroceryItemDTO dto = new GroceryItemDTO(item);
            ResponseData<GroceryItemDTO> res = ResponseData.<GroceryItemDTO>builder()
                    .entity(dto)
                    .success(true)
                    .successMsg("Retrieved grocery item with id " + id)
                    .failedMsg(null)
                    .build();

            return res;
    }

    @Override
    public ResponseData<List<GroceryItemDTO>> getGroceryItems(String name, Double price, String category, int page, int size) {
        List<GroceryItemDTO> itemDTOList = new ArrayList<>();
        List<GroceryItem> groceryItems = this.groceryStoreRepository.getGroceryItems(name, price, category, page, size);

        for(GroceryItem item : groceryItems) {
            itemDTOList.add(new GroceryItemDTO(item));
        }
        ResponseData<List<GroceryItemDTO>> res = ResponseData.<List<GroceryItemDTO>>builder()
                .entity(itemDTOList)
                .success(true)
                .successMsg("Retrieved grocery items")
                .failedMsg(null)
                .build();

        return res;
    }

    @Override
    public ResponseData<GroceryItemDTO> updateGroceryItem(Long id, UpdateGroceryItemDTO dto) {
        if(StringUtils.isBlank(dto.getName())) {
            throw new IllegalArgumentException("Item to be updated must have a name");
        }
        if(BigDecimal.ZERO.equals(dto.getPrice())) {
            throw new IllegalArgumentException("Item to be updated must have a valid price");
        }
        GroceryItem item =  groceryStoreRepository.updateGroceryItem(id, dto);
        GroceryItemDTO itemDTO = new GroceryItemDTO(item);

        ResponseData<GroceryItemDTO> res = ResponseData.<GroceryItemDTO>builder()
                .entity(itemDTO)
                .success(true)
                .successMsg("Updated item successfully")
                .failedMsg(null)
                .build();

        return res;
    }

    @Override
    public GroceryItemDTO updateGroceryItem(int id, GroceryItemDTO dto) {
        return null;
    }

    private void validateGroceryItem(GroceryItemDTO dto) throws InvalidPayloadException {
        if(Objects.isNull(dto)) {
            throw new InvalidPayloadException("Grocery Item to be created cannot be null");
        }
        if(StringUtils.isBlank(dto.getName())) {
            throw new InvalidPayloadException("Grocery Item cannot have empty name");
        }
        if(StringUtils.isBlank(dto.getCategory())) {
            throw new InvalidPayloadException("Grocery Item must belong to a category");
        }
        if(BigDecimal.ZERO.equals(dto.getPrice())) {
            throw new InvalidPayloadException("Grocery Item cannot have 0 price");
        }
        if(GroceryCategoryEnum.getCategoryValue(dto.getCategory()) == null) {
            throw new InvalidPayloadException("Grocery category not found");
        }
    }

    private void populateGroceryItem(GroceryItemDTO dto) {
        dto.setCategory(GroceryCategoryEnum.getCategoryValue(dto.getCategory()));
    }
}
