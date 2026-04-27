package com.sammy.belajar_spring.repository;

import com.sammy.belajar_spring.entity.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Menandakan ini repository Spring
@Repository

// JpaRepository<Entity, TipePrimaryKey>
public interface OrderHeaderRepository
        extends JpaRepository<OrderHeader, Long> {

}