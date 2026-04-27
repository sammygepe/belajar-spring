package com.sammy.belajar_spring.repository;

import com.sammy.belajar_spring.entity.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderHeaderRepository
        extends JpaRepository<OrderHeader, Long> {

    List<OrderHeader> findByInvoiceNoContainingIgnoreCase(String invoiceNo);
}