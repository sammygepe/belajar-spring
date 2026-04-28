package com.sammy.belajar_spring.service;

import com.sammy.belajar_spring.dto.DashboardSummaryResponse;
import com.sammy.belajar_spring.repository.OrderHeaderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.sammy.belajar_spring.dto.TopCustomerResponse;
import org.springframework.data.domain.PageRequest;
import java.util.List;

@Service
public class DashboardService {

    private final OrderHeaderRepository orderHeaderRepository;

    public DashboardService(OrderHeaderRepository orderHeaderRepository) {
        this.orderHeaderRepository = orderHeaderRepository;
    }

    public DashboardSummaryResponse getSummary() {

        long totalOrders = orderHeaderRepository.count();

        BigDecimal totalRevenue =
                orderHeaderRepository.getTotalRevenue();

        BigDecimal averageOrder =
                totalOrders == 0
                        ? BigDecimal.ZERO
                        : totalRevenue.divide(
                        BigDecimal.valueOf(totalOrders),
                        2,
                        RoundingMode.HALF_UP
                );

        return new DashboardSummaryResponse(
                totalOrders,
                totalRevenue,
                averageOrder
        );
    }

    public List<TopCustomerResponse> getTopCustomers() {

        return orderHeaderRepository.getTopCustomers(
                PageRequest.of(0, 5)
        );
    }
}