package com.sammy.belajar_spring.repository;

import com.sammy.belajar_spring.dto.TopCustomerResponse;
import com.sammy.belajar_spring.entity.OrderHeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface OrderHeaderRepository
        extends JpaRepository<OrderHeader, Long> {

    // Search invoice biasa
    List<OrderHeader> findByInvoiceNoContainingIgnoreCase(String invoiceNo);

    // Search invoice + pagination
    Page<OrderHeader> findByInvoiceNoContainingIgnoreCase(
            String invoiceNo,
            Pageable pageable
    );

    // Dashboard total revenue
    @Query("SELECT COALESCE(SUM(o.grandTotal),0) FROM OrderHeader o")
    BigDecimal getTotalRevenue();

    // Top customer
    @Query("""
SELECT new com.sammy.belajar_spring.dto.TopCustomerResponse(
    u.nama,
    COUNT(o),
    COALESCE(SUM(o.grandTotal),0)
)
FROM OrderHeader o
JOIN o.user u
GROUP BY u.id, u.nama
ORDER BY COALESCE(SUM(o.grandTotal),0) DESC
""")
    List<TopCustomerResponse> getTopCustomers(Pageable pageable);
}