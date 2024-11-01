package com.qp.grocery.controllers;

import com.qp.grocery.entities.CreateOrderDTO;
import com.qp.grocery.exceptions.ItemNotFoundException;
import com.qp.grocery.services.IOrderService;
import com.qp.grocery.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController implements IOrderController{

    @Autowired
    private IOrderService orderService;

    @Override
    @PostMapping("/")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderDTO dto) throws ItemNotFoundException {
        ResponseData<CreateOrderDTO> apiRes = this.orderService.createOrder(dto);

        return new ResponseEntity<>(apiRes, HttpStatus.CREATED);
    }
}
