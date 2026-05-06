package com.sammy.belajar_spring.controller;

import com.sammy.belajar_spring.dto.ApiResponse;
import com.sammy.belajar_spring.dto.CreateOrderRequest;
import com.sammy.belajar_spring.dto.OrderResponse; // ✅ DTO
import com.sammy.belajar_spring.dto.PageResponse;

import com.sammy.belajar_spring.service.OrderService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // ==========================================
    // CREATE ORDER
    // ==========================================
    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(
            @Valid @RequestBody CreateOrderRequest request
    ) {

        OrderResponse savedOrder =
                orderService.createOrder(request); // ✅ sudah DTO

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        "Order created successfully",
                        savedOrder
                ));
    }

    // ==========================================
    // GET ALL
    // ==========================================
    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getAllOrders() {

        List<OrderResponse> orders =
                orderService.getAllOrders();

        return ResponseEntity.ok(
                new ApiResponse<>("Success get orders", orders)
        );
    }

    // ==========================================
    // GET BY ID
    // ==========================================
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrderById(
            @PathVariable Long id
    ) {

        OrderResponse order =
                orderService.getOrderById(id);

        return ResponseEntity.ok(
                new ApiResponse<>("Success get order", order)
        );
    }

    // ==========================================
    // DELETE
    // ==========================================
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteOrder(
            @PathVariable Long id
    ) {

        orderService.deleteOrder(id);

        return ResponseEntity.ok(
                new ApiResponse<>("Order deleted successfully", null)
        );
    }

    // ==========================================
    // SEARCH
    // ==========================================
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> searchOrder(
            @RequestParam String invoice
    ) {

        List<OrderResponse> orders =
                orderService.searchByInvoice(invoice);

        return ResponseEntity.ok(
                new ApiResponse<>("Success search order", orders)
        );
    }

    // ==========================================
    // PAGINATION
    // ==========================================
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<PageResponse<OrderResponse>>> getOrdersPage(
            @PageableDefault(size = 5, sort = "id")
            Pageable pageable
    ) {

        Page<OrderResponse> result =
                orderService.getOrders(pageable);

        PageResponse<OrderResponse> pageResponse =
                new PageResponse<>(
                        result.getContent(),
                        result.getNumber(),
                        result.getSize(),
                        result.getTotalElements(),
                        result.getTotalPages(),
                        result.isLast()
                );

        return ResponseEntity.ok(
                new ApiResponse<>("Success get paged orders", pageResponse)
        );
    }

    // ==========================================
    // SEARCH + PAGINATION
    // ==========================================
    @GetMapping("/search/page")
    public ResponseEntity<ApiResponse<PageResponse<OrderResponse>>> searchOrdersPage(
            @RequestParam String invoice,
            @PageableDefault(size = 5, sort = "id")
            Pageable pageable
    ) {

        Page<OrderResponse> result =
                orderService.searchOrders(invoice, pageable);

        PageResponse<OrderResponse> pageResponse =
                new PageResponse<>(
                        result.getContent(),
                        result.getNumber(),
                        result.getSize(),
                        result.getTotalElements(),
                        result.getTotalPages(),
                        result.isLast()
                );

        return ResponseEntity.ok(
                new ApiResponse<>("Success search paged orders", pageResponse)
        );
    }
}