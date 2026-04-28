package com.sammy.belajar_spring.controller;

import com.sammy.belajar_spring.dto.ApiResponse;
import com.sammy.belajar_spring.dto.DashboardSummaryResponse;
import com.sammy.belajar_spring.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sammy.belajar_spring.dto.TopCustomerResponse;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(
            DashboardService dashboardService
    ) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<DashboardSummaryResponse>>
    getSummary() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Success get dashboard summary",
                        dashboardService.getSummary()
                )
        );
    }

    @GetMapping("/top-customers")
    public ResponseEntity<ApiResponse<List<TopCustomerResponse>>>
    getTopCustomers() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Success get top customers",
                        dashboardService.getTopCustomers()
                )
        );
    }
}