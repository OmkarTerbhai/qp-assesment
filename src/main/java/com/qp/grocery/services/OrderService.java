package com.qp.grocery.services;

import com.qp.grocery.entities.CreateOrderDTO;
import com.qp.grocery.entities.GroceryOrder;
import com.qp.grocery.exceptions.ItemNotFoundException;
import com.qp.grocery.repositories.OrderDAO;
import com.qp.grocery.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderDAO orderRepository;

    @Override
    public ResponseData<CreateOrderDTO> createOrder(CreateOrderDTO dto) throws ItemNotFoundException {
        dto.setPrice(BigDecimal.TEN);
        GroceryOrder groceryOrder = this.orderRepository.createOrder(dto);
        dto.setId(groceryOrder.getId());
        ResponseData<CreateOrderDTO> res = ResponseData.<CreateOrderDTO>builder()
                .entity(dto)
                .success(true)
                .successMsg("Created order successfully with id " + dto.getId())
                .failedMsg(null)
                .build();

        return res;
    }

    @Override
    public ResponseData<CreateOrderDTO> deleteOrder(Long id) throws ItemNotFoundException {
        GroceryOrder groceryOrder = this.orderRepository.deleteOrder(id);

        CreateOrderDTO dto = new CreateOrderDTO(groceryOrder);

        ResponseData<CreateOrderDTO> res = ResponseData.<CreateOrderDTO>builder()
                .entity(dto)
                .success(true)
                .successMsg("Deleted order with id : " + id)
                .failedMsg(null)
                .build();

        return res;
    }
}
