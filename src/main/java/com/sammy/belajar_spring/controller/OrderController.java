package com.sammy.belajar_spring.controller;

import com.sammy.belajar_spring.dto.ApiResponse;
import com.sammy.belajar_spring.dto.CreateOrderRequest;
import com.sammy.belajar_spring.entity.OrderHeader;
import com.sammy.belajar_spring.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// REST API order
@RestController

// Base URL
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    // Constructor Injection
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // CREATE ORDER
    @PostMapping
    public ResponseEntity<ApiResponse<OrderHeader>> createOrder(
            @Valid @RequestBody CreateOrderRequest request
    ) {

        // Panggil service
        OrderHeader savedOrder =
                orderService.createOrder(request);

        // Return response JSON
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new ApiResponse<>(
                                "Order created successfully",
                                savedOrder
                        )
                );
    }

    // GET ALL ORDERS
    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderHeader>>> getAllOrders() {

        List<OrderHeader> orders =
                orderService.getAllOrders();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Success get orders",
                        orders
                )
        );
    }

    // GET ORDER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderHeader>> getOrderById(
            @PathVariable Long id
    ) {

        OrderHeader order =
                orderService.getOrderById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Success get order",
                        order
                )
        );
    }

    // DELETE ORDER
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteOrder(
            @PathVariable Long id
    ) {

        orderService.deleteOrder(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Order deleted successfully",
                        null
                )
        );
    }
}