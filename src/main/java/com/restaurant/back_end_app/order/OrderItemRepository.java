package com.restaurant.back_end_app.order;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    @Transactional
    void deleteByFoodId(Long id);
}
