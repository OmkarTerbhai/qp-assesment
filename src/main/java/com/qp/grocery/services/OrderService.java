package com.qp.grocery.services;

import com.qp.grocery.dtos.CreateOrderDTO;
import com.qp.grocery.exceptions.ItemNotFoundException;
import com.qp.grocery.utils.ResponseData;

public interface OrderService {

    ResponseData<CreateOrderDTO> createOrder(CreateOrderDTO dto) throws ItemNotFoundException;

    ResponseData<CreateOrderDTO> deleteOrder(Long id) throws ItemNotFoundException;
}
