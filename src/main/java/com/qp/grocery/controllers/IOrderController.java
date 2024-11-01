package com.qp.grocery.controllers;

import com.qp.grocery.entities.CreateOrderDTO;
import com.qp.grocery.exceptions.ItemNotFoundException;
import org.springframework.http.ResponseEntity;

public interface IOrderController {

    ResponseEntity<?> createOrder(CreateOrderDTO dto) throws ItemNotFoundException;
}
