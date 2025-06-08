package com.uniplaystore.uniplay_backend.order;

import java.time.LocalDateTime;

public record OrderResponseDTO(Long id, Long userId, LocalDateTime orderDate, double totalAmount, String status) {
    public OrderResponseDTO(Order order){
        this(order.getId(), order.getUserId(), order.getOrderDate(), order.getTotalAmount(), order.getStatus());
    }
}
