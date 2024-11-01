package com.qp.grocery.repositories;

import com.qp.grocery.entities.CreateOrderDTO;
import com.qp.grocery.entities.GroceryOrder;
import com.qp.grocery.exceptions.ItemNotFoundException;

public interface IOrderDAO {
    GroceryOrder createOrder(CreateOrderDTO dto) throws ItemNotFoundException;

    GroceryOrder deleteOrder(Long id) throws ItemNotFoundException;
}
