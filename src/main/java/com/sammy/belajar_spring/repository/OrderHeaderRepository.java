package com.sammy.belajar_spring.repository;

import com.sammy.belajar_spring.entity.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

import com.sammy.belajar_spring.dto.TopCustomerResponse;
import org.springframework.data.domain.Pageable;

public interface OrderHeaderRepository
        extends JpaRepository<OrderHeader, Long> {

    List<OrderHeader> findByInvoiceNoContainingIgnoreCase(String invoiceNo);

    @Query("SELECT COALESCE(SUM(o.grandTotal),0) FROM OrderHeader o")
    BigDecimal getTotalRevenue();

    @Query("""
SELECT new com.sammy.belajar_spring.dto.TopCustomerResponse(
    o.user.nama,
    COUNT(o),
    SUM(o.grandTotal)
)
FROM OrderHeader o
GROUP BY o.user.nama
ORDER BY SUM(o.grandTotal) DESC
""")
    List<TopCustomerResponse> getTopCustomers(Pageable pageable);
}