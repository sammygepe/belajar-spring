package com.sammy.belajar_spring.repository;

import com.sammy.belajar_spring.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository untuk tabel order_details
@Repository
public interface OrderDetailRepository
        extends JpaRepository<OrderDetail, Long> {

}