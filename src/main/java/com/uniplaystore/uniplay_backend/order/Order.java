package com.uniplaystore.uniplay_backend.order;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "orders")
@Entity(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    @Column(name = "total_amount")
    private double totalAmount;
    @Column
    private String status;

    public Order(OrderRequestDTO data) {
        this.userId = data.userId();
        this.orderDate = LocalDateTime.now(); // Data do pedido é definida na criação
        this.totalAmount = data.totalAmount();
        this.status = data.status();
    }
}
