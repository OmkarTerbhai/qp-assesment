package com.qp.grocery.repositories;

import com.qp.grocery.entities.GroceryItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroceryRepository extends JpaRepository<GroceryItem, Long> {

    @Query("SELECT g FROM GroceryItem g " +
            "WHERE (:name IS NULL OR g.name LIKE %:name%) " +
            "AND (:category IS NULL OR g.category LIKE %:category%) " +
            "AND (:price IS NULL OR g.price = :price)")
    Page<GroceryItem> getItems(@Param("name") String name,
                               @Param("category") String category,
                               @Param("price") Double price,
                               Pageable page);
}
