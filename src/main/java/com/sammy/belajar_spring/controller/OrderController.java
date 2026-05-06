package com.sammy.belajar_spring.controller;

import com.sammy.belajar_spring.dto.ApiResponse;
import com.sammy.belajar_spring.dto.CreateOrderRequest;
import com.sammy.belajar_spring.dto.OrderResponse;
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

/**
 * 🎯 OrderController
 *
 * Berfungsi sebagai layer API (entry point dari client)
 * - Menerima request dari client (Postman / Frontend)
 * - Mengirim ke service untuk diproses
 * - Mengembalikan response dalam bentuk JSON (ApiResponse)
 *
 * ❗ Controller TIDAK boleh berisi business logic
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    // Service untuk handle business logic
    private final OrderService orderService;

    // Constructor Injection (best practice Spring)
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // ==========================================
    // CREATE ORDER
    // ==========================================
    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(

            // 🔥 @Valid → trigger validation dari DTO
            // 🔥 @RequestBody → ambil JSON dari request
            @Valid @RequestBody CreateOrderRequest request
    ) {

        // Kirim request DTO ke service
        OrderResponse savedOrder =
                orderService.createOrder(request);

        // Return HTTP 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        "Order created successfully",
                        savedOrder
                ));
    }

    // ==========================================
    // GET ALL ORDERS
    // ==========================================
    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getAllOrders() {

        // Ambil semua data (sudah dalam bentuk DTO)
        List<OrderResponse> orders =
                orderService.getAllOrders();

        return ResponseEntity.ok(
                new ApiResponse<>("Success get orders", orders)
        );
    }

    // ==========================================
    // GET ORDER BY ID
    // ==========================================
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrderById(

            // Ambil path variable dari URL
            @PathVariable Long id
    ) {

        OrderResponse order =
                orderService.getOrderById(id);

        return ResponseEntity.ok(
                new ApiResponse<>("Success get order", order)
        );
    }

    // ==========================================
    // DELETE ORDER
    // ==========================================
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteOrder(
            @PathVariable Long id
    ) {

        // Hapus data
        orderService.deleteOrder(id);

        return ResponseEntity.ok(
                new ApiResponse<>("Order deleted successfully", null)
        );
    }

    // ==========================================
    // SEARCH ORDER BY INVOICE
    // ==========================================
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> searchOrder(

            // Ambil query param: /orders/search?invoice=INV
            @RequestParam String invoice
    ) {

        List<OrderResponse> orders =
                orderService.searchByInvoice(invoice);

        return ResponseEntity.ok(
                new ApiResponse<>("Success search order", orders)
        );
    }

    // ==========================================
    // PAGINATION (GET ALL)
    // ==========================================
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<PageResponse<OrderResponse>>> getOrdersPage(

            /**
             * Pageable:
             * - size = jumlah data per halaman
             * - sort = sorting berdasarkan field
             *
             * Contoh:
             * /orders/page?page=0&size=5
             */
            @PageableDefault(size = 5, sort = "id")
            Pageable pageable
    ) {

        // Ambil data dalam bentuk Page
        Page<OrderResponse> result =
                orderService.getOrders(pageable);

        /**
         * Convert Page → Custom PageResponse
         * supaya JSON lebih clean & mudah dipakai frontend
         */
        PageResponse<OrderResponse> pageResponse =
                new PageResponse<>(
                        result.getContent(),       // isi data
                        result.getNumber(),        // halaman saat ini
                        result.getSize(),          // jumlah per halaman
                        result.getTotalElements(), // total data
                        result.getTotalPages(),    // total halaman
                        result.isLast()            // apakah halaman terakhir
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

        // Ambil hasil search dalam bentuk page
        Page<OrderResponse> result =
                orderService.searchOrders(invoice, pageable);

        // Mapping ke PageResponse
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