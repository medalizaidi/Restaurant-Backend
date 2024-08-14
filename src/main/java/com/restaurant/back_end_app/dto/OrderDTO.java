package com.restaurant.back_end_app.dto;

import com.restaurant.back_end_app.order.Status;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class OrderDTO {


    private Long userId;
    private List<OrderItemDTO> items;



    // Getters and setters
}
