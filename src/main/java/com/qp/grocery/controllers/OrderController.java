package com.qp.grocery.controllers;

import com.qp.grocery.dtos.CreateOrderDTO;
import com.qp.grocery.exceptions.InvalidPayloadException;
import com.qp.grocery.exceptions.ItemNotFoundException;
import org.springframework.http.ResponseEntity;

public interface OrderController {

    ResponseEntity<?> createOrder(CreateOrderDTO dto) throws ItemNotFoundException, InvalidPayloadException;

    ResponseEntity<?> listGroceryItems(String name, Double price, String category, int page, int size);

    ResponseEntity<?> deleteOrder(Long id) throws ItemNotFoundException;

}
