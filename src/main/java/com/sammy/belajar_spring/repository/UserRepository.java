package com.sammy.belajar_spring.repository;

import com.sammy.belajar_spring.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<User, Long>
// User = entity
// Long = tipe data primary key
public interface UserRepository extends JpaRepository<User, Long> {

    // ==========================================
    // SEARCH USER BERDASARKAN NAMA
    // ==========================================
    // Contoh:
    // keyword = sam
    // hasil = sammy, samsul, kasam, dll
    //
    // IgnoreCase = tidak peduli huruf besar kecil
    // Pageable = support pagination + sorting
    Page<User> findByNamaContainingIgnoreCase(String keyword, Pageable pageable);
}