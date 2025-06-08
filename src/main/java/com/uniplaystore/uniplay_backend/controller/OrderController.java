package com.uniplaystore.uniplay_backend.controller;

import com.uniplaystore.uniplay_backend.order.Order;
import com.uniplaystore.uniplay_backend.order.OrderRequestDTO;
import com.uniplaystore.uniplay_backend.order.OrderResponseDTO;
import com.uniplaystore.uniplay_backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/create")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody @Valid OrderRequestDTO data) {
        Order newOrder = new Order(data);
        orderRepository.save(newOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(new OrderResponseDTO(newOrder));
    }

    @GetMapping("/list")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<OrderResponseDTO> orders = orderRepository.findAll()
                .stream()
                .map(OrderResponseDTO::new)
                .toList();
        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(value -> ResponseEntity.ok(new OrderResponseDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByUserId(@PathVariable Long userId) {
        List<OrderResponseDTO> orders = orderRepository.findByUserId(userId)
                .stream()
                .map(OrderResponseDTO::new)
                .toList();
        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> updateOrder(@PathVariable Long id, @RequestBody @Valid OrderRequestDTO data) {
        Optional<Order> existingOrderOptional = orderRepository.findById(id);
        if (existingOrderOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Order existingOrder = existingOrderOptional.get();
        existingOrder.setUserId(data.userId());
        existingOrder.setTotalAmount(data.totalAmount());
        existingOrder.setStatus(data.status());

        orderRepository.save(existingOrder);
        return ResponseEntity.ok(new OrderResponseDTO(existingOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (!orderRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        orderRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
