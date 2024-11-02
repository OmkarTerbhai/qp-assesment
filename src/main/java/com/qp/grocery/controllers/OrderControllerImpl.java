package com.qp.grocery.controllers;

import com.qp.grocery.dtos.CreateOrderDTO;
import com.qp.grocery.dtos.GroceryItemDTO;
import com.qp.grocery.exceptions.ItemNotFoundException;
import com.qp.grocery.services.GroceryService;
import com.qp.grocery.services.OrderService;
import com.qp.grocery.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderControllerImpl implements OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private GroceryService groceryService;

    @Override
    @PostMapping("/")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderDTO dto) throws ItemNotFoundException {
        ResponseData<CreateOrderDTO> apiRes = this.orderService.createOrder(dto);

        return new ResponseEntity<>(apiRes, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> listGroceryItems(String name, Double price, String category, int page, int size) {
        ResponseData<List<GroceryItemDTO>> apiRes = groceryService.getGroceryItems(name, price, category, page, size);
        ResponseEntity<ResponseData<List<GroceryItemDTO>>> response = new ResponseEntity<>(apiRes, HttpStatus.OK);

        return response;
    }
}
