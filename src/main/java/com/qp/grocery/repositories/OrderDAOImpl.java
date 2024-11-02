package com.qp.grocery.repositories;

import com.qp.grocery.entities.GroceryOrder;
import com.qp.grocery.exceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GroceryRepository groceryRepository;

    @Override
    @Transactional
    public GroceryOrder createOrder(GroceryOrder groceryOrder) throws ItemNotFoundException {
        this.orderRepository.save(groceryOrder);
        return groceryOrder;
    }

    @Override
    public GroceryOrder deleteOrder(Long id) throws ItemNotFoundException {
        GroceryOrder groceryOrder = this.orderRepository.findById(id)
                .orElseThrow(() ->
                        new ItemNotFoundException("Could not find item with id " + id));

        groceryOrder.getItems().forEach(item -> item.setGroceryOrder(null));

        this.orderRepository.deleteById(id);

        return groceryOrder;
    }
}
