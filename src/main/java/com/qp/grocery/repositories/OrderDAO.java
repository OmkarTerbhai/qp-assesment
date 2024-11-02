package com.qp.grocery.repositories;

import com.qp.grocery.dtos.CreateOrderDTO;
import com.qp.grocery.entities.GroceryOrder;
import com.qp.grocery.exceptions.ItemNotFoundException;

public interface OrderDAO {
    GroceryOrder createOrder(CreateOrderDTO dto) throws ItemNotFoundException;

    GroceryOrder deleteOrder(Long id) throws ItemNotFoundException;
}
