package com.qp.grocery.repositories;

import com.qp.grocery.dtos.CreateOrderDTO;
import com.qp.grocery.entities.GroceryItem;
import com.qp.grocery.dtos.ItemOrderDTO;
import com.qp.grocery.entities.GroceryOrder;
import com.qp.grocery.exceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GroceryRepository groceryRepository;

    @Override
    public GroceryOrder createOrder(CreateOrderDTO dto) throws ItemNotFoundException {
        GroceryOrder groceryOrder = getOrderFromDTO(dto);
        this.orderRepository.save(groceryOrder);
        return groceryOrder;
    }

    @Override
    public GroceryOrder deleteOrder(Long id) throws ItemNotFoundException {
        GroceryOrder groceryOrder = this.orderRepository.findById(id)
                .orElseThrow(() ->
                        new ItemNotFoundException("Could not find item with id " + id));

        this.orderRepository.deleteById(id);

        return groceryOrder;
    }

    private GroceryOrder getOrderFromDTO(CreateOrderDTO dto) throws ItemNotFoundException {
        GroceryOrder groceryOrder = new GroceryOrder();
        groceryOrder.setOrderPrice(dto.getPrice());
        groceryOrder.setItems(new ArrayList<>());
        for(ItemOrderDTO itemOrderDTO : dto.getItems()) {
            GroceryItem item = groceryRepository.findById(itemOrderDTO.getItemId())
                    .orElseThrow(() ->
                            new ItemNotFoundException("Could not find item with id " + itemOrderDTO.getItemId()));

            item.setGroceryOrder(groceryOrder);
            groceryOrder.getItems().add(item);
        }
        return groceryOrder;
    }
}
