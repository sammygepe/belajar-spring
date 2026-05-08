package com.sammy.belajar_spring.integration;

import com.sammy.belajar_spring.dto.CreateOrderRequest;
import com.sammy.belajar_spring.dto.CreateOrderDetailRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.sammy.belajar_spring.entity.User;

import com.sammy.belajar_spring.repository.OrderDetailRepository;
import com.sammy.belajar_spring.repository.OrderHeaderRepository;
import com.sammy.belajar_spring.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 🔥 Integration Test
 *
 * Test:
 * - controller asli
 * - service asli
 * - repository asli
 * - database asli
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class OrderIntegrationTest {

    // ==========================================
    // MOCK MVC
    // ==========================================
    @Autowired
    private MockMvc mockMvc;

    // ==========================================
    // OBJECT MAPPER
    // ==========================================
    @Autowired
    private ObjectMapper objectMapper;

    // ==========================================
    // REPOSITORIES
    // ==========================================
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderHeaderRepository orderHeaderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    // ==========================================
    // PASSWORD ENCODER
    // ==========================================
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // ==========================================
    // TEST USER
    // ==========================================
    private User testUser;

    // ==========================================
    // SETUP DATABASE
    // ==========================================
    @BeforeEach
    void setup() {

        // delete child table dulu
        orderDetailRepository.deleteAll();

        orderHeaderRepository.deleteAll();

        userRepository.deleteAll();

        // ======================================
        // CREATE TEST USER
        // ======================================

        User user = new User();

        user.setNama("Sammy");
        user.setUmur(25);
        user.setUsername("sammy");

        user.setPassword(
                passwordEncoder.encode("123")
        );

        user.setRole("USER");

        testUser = userRepository.save(user);
    }

    // ==========================================
    // TEST CREATE ORDER
    // ==========================================
    @Test
    void create_order_success() throws Exception {

        // ======================================
        // CREATE DETAIL REQUEST
        // ======================================

        CreateOrderDetailRequest detail =
                new CreateOrderDetailRequest();

        detail.setItemName("Indomie");
        detail.setQty(2);
        detail.setPrice(
                BigDecimal.valueOf(3000)
        );

        // ======================================
        // CREATE ORDER REQUEST
        // ======================================

        CreateOrderRequest request =
                new CreateOrderRequest();

        request.setUserId(testUser.getId());

        request.setInvoiceNo("INV-001");

        request.setOrderDate(
                LocalDate.now()
        );

        request.setDetails(
                List.of(detail)
        );

        // ======================================
        // EXECUTE REQUEST
        // ======================================

        mockMvc.perform(

                        post("/orders")

                                .contentType(
                                        MediaType.APPLICATION_JSON
                                )

                                .content(
                                        objectMapper.writeValueAsString(request)
                                )
                )

                // ==================================
                // ASSERT RESPONSE
                // ==================================

                .andExpect(status().isCreated())

                // message response
                .andExpect(
                        jsonPath("$.message")
                                .value("Order created successfully")
                );

        // ======================================
        // ASSERT DATABASE
        // ======================================

        // header harus tersimpan
        assert(orderHeaderRepository.count() == 1);

        // detail harus tersimpan
        assert(orderDetailRepository.count() == 1);
    }
}