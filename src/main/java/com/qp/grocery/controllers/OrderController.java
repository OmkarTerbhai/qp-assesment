package com.qp.grocery.controllers;

import com.qp.grocery.dtos.CreateOrderDTO;
import com.qp.grocery.exceptions.ItemNotFoundException;
import org.springframework.http.ResponseEntity;

public interface OrderController {

    ResponseEntity<?> createOrder(CreateOrderDTO dto) throws ItemNotFoundException;

    ResponseEntity<?> listGroceryItems(String name, Double price, String category, int page, int size);


}
