package com.sammy.belajar_spring.repository;

import com.sammy.belajar_spring.entity.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface OrderHeaderRepository
        extends JpaRepository<OrderHeader, Long> {

    List<OrderHeader> findByInvoiceNoContainingIgnoreCase(String invoiceNo);

    @Query("SELECT COALESCE(SUM(o.grandTotal),0) FROM OrderHeader o")
    BigDecimal getTotalRevenue();
}