package com.sammy.belajar_spring.service;

import com.sammy.belajar_spring.dto.CreateOrderDetailRequest;
import com.sammy.belajar_spring.dto.CreateOrderRequest;
import com.sammy.belajar_spring.dto.OrderResponse;
import com.sammy.belajar_spring.entity.OrderDetail;
import com.sammy.belajar_spring.entity.OrderHeader;
import com.sammy.belajar_spring.entity.User;
import com.sammy.belajar_spring.exception.UserNotFoundException;
import com.sammy.belajar_spring.repository.OrderHeaderRepository;
import com.sammy.belajar_spring.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// Menandakan class service
@Service
public class OrderService {

    private final OrderHeaderRepository orderHeaderRepository;
    private final UserRepository userRepository;

    // Constructor Injection
    public OrderService(OrderHeaderRepository orderHeaderRepository,
                        UserRepository userRepository) {
        this.orderHeaderRepository = orderHeaderRepository;
        this.userRepository = userRepository;
    }

    // Transaction:
    // jika salah satu gagal, semua dibatalkan
    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {

        // Cari user berdasarkan id
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User id " + request.getUserId() + " tidak ditemukan"
                        ));

        // Buat object header
        OrderHeader header = new OrderHeader();
        header.setInvoiceNo(request.getInvoiceNo());
        header.setOrderDate(request.getOrderDate());
        header.setUser(user);

        // List detail tampungan
        List<OrderDetail> detailList = new ArrayList<>();

        // Total awal = 0
        BigDecimal grandTotal = BigDecimal.ZERO;

        // Loop semua item detail
        for (CreateOrderDetailRequest item : request.getDetails()) {

            // subtotal = qty x price
            BigDecimal subtotal =
                    item.getPrice().multiply(
                            BigDecimal.valueOf(item.getQty())
                    );

            // Buat detail entity
            OrderDetail detail = new OrderDetail();
            detail.setItemName(item.getItemName());
            detail.setQty(item.getQty());
            detail.setPrice(item.getPrice());
            detail.setSubtotal(subtotal);

            // Hubungkan detail ke header
            detail.setOrderHeader(header);

            // Masukkan ke list
            detailList.add(detail);

            // Tambah grand total
            grandTotal = grandTotal.add(subtotal);
        }

        // Set detail ke header
        header.setDetails(detailList);

        // Set grand total
        header.setGrandTotal(grandTotal);

        // Save header
        // detail ikut save otomatis (cascade)
        // return orderHeaderRepository.save(header);
        OrderHeader saved = orderHeaderRepository.save(header);
        return mapToResponse(saved);
    }

    // Ambil semua order
    public List<OrderResponse> getAllOrders() {
        return orderHeaderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Ambil order by id
    public OrderResponse getOrderById(Long id) {
        OrderHeader order = orderHeaderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order id " + id + " tidak ditemukan")
                );

        return mapToResponse(order);
    }

    // DELETE ORDER
    @Transactional
    public void deleteOrder(Long id) {

        OrderHeader order = orderHeaderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Order id " + id + " tidak ditemukan"
                        ));

        // Hapus header
        // Detail ikut terhapus karena cascade
        orderHeaderRepository.delete(order);
    }

    // SEARCH BY INVOICE
    public List<OrderResponse> searchByInvoice(String invoice) {
        return orderHeaderRepository
                .findByInvoiceNoContainingIgnoreCase(invoice)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // GET ALL ORDERS PAGINATION
    public Page<OrderResponse> getOrders(Pageable pageable) {
        return orderHeaderRepository
                .findAll(pageable)
                .map(this::mapToResponse);
    }

    // SEARCH INVOICE PAGINATION
    public Page<OrderResponse> searchOrders(String invoiceNo, Pageable pageable) {
        return orderHeaderRepository
                .findByInvoiceNoContainingIgnoreCase(invoiceNo, pageable)
                .map(this::mapToResponse);
    }

    private OrderResponse mapToResponse(OrderHeader order) {
        return new OrderResponse(
                order.getId(),
                order.getInvoiceNo(),                // ✅ FIX
                order.getUser().getNama(),           // ✅ ambil dari user
                order.getGrandTotal()                // ✅ FIX
        );
    }
}