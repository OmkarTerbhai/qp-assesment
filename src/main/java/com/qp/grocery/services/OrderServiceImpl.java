package com.qp.grocery.services;

import com.qp.grocery.dtos.CreateOrderDTO;
import com.qp.grocery.dtos.ItemOrderDTO;
import com.qp.grocery.entities.GroceryItem;
import com.qp.grocery.entities.GroceryOrder;
import com.qp.grocery.exceptions.InvalidPayloadException;
import com.qp.grocery.exceptions.ItemNotFoundException;
import com.qp.grocery.repositories.GroceryStoreDAO;
import com.qp.grocery.repositories.OrderDAOImpl;
import com.qp.grocery.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAOImpl orderRepository;

    @Autowired
    private GroceryStoreDAO groceryStoreDAO;

    @Override
    public ResponseData<CreateOrderDTO> createOrder(CreateOrderDTO dto) throws ItemNotFoundException, InvalidPayloadException {

        GroceryOrder groceryOrder = this.orderRepository.createOrder(getOrderFromDTO(dto));
        dto.setId(groceryOrder.getId());
        ResponseData<CreateOrderDTO> res = ResponseData.<CreateOrderDTO>builder()
                .entity(dto)
                .success(true)
                .successMsg("Created order successfully with id " + dto.getId())
                .failedMsg(null)
                .build();

        return res;
    }

    @Override
    public ResponseData<CreateOrderDTO> deleteOrder(Long id) throws ItemNotFoundException {
        GroceryOrder groceryOrder = this.orderRepository.deleteOrder(id);

        CreateOrderDTO dto = new CreateOrderDTO(groceryOrder);

        ResponseData<CreateOrderDTO> res = ResponseData.<CreateOrderDTO>builder()
                .entity(dto)
                .success(true)
                .successMsg("Deleted order with id : " + id)
                .failedMsg(null)
                .build();

        return res;
    }

    private GroceryOrder getOrderFromDTO(CreateOrderDTO dto) throws ItemNotFoundException, InvalidPayloadException {

        BigDecimal orderPrice = BigDecimal.ZERO;

        GroceryOrder groceryOrder = new GroceryOrder();
        groceryOrder.setItems(new ArrayList<>());
        for(ItemOrderDTO itemOrderDTO : dto.getItems()) {
            GroceryItem item = groceryStoreDAO.getGroceryItem(itemOrderDTO.getItemId());

            if(item.getInventoryCount() < itemOrderDTO.getCount()) {
                throw new InvalidPayloadException("We are out of stock for " + item.getName());
            }
            orderPrice = orderPrice.add(item.getPrice().multiply(BigDecimal.valueOf(itemOrderDTO.getCount())));
            item.setGroceryOrder(groceryOrder);
            groceryOrder.getItems().add(item);
        }
        groceryOrder.setOrderPrice(orderPrice);
        return groceryOrder;
    }
}
