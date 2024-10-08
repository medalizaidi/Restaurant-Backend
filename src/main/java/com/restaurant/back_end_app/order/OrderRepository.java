package com.restaurant.back_end_app.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("SELECT o FROM Order o WHERE o.client.id = :userId")
    List<Order> findOrdersByUserId(@Param("userId") Long userId);

}
