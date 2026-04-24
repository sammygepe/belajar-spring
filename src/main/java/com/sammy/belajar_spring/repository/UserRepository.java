package com.sammy.belajar_spring.repository;

import com.sammy.belajar_spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<User, Long>
// User = entity
// Long = tipe id
public interface UserRepository extends JpaRepository<User, Long> {
}