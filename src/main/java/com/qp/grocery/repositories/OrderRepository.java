package com.qp.grocery.repositories;

import com.qp.grocery.entities.GroceryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<GroceryOrder, Long> {
}
