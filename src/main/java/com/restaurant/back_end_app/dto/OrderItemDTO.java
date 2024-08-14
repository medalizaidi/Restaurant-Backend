package com.restaurant.back_end_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class OrderItemDTO {
    private Long foodId;
    private Integer quantity;
}
