package com.qp.grocery.services;

import com.qp.grocery.entities.CreateOrderDTO;
import com.qp.grocery.exceptions.ItemNotFoundException;
import com.qp.grocery.utils.ResponseData;

public interface IOrderService {

    ResponseData<CreateOrderDTO> createOrder(CreateOrderDTO dto) throws ItemNotFoundException;

    ResponseData<CreateOrderDTO> deleteOrder(Long id) throws ItemNotFoundException;
}
