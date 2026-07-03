package com.example.CarDealership.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.example.CarDealership.model.Sale;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record SaleRequest(

        @NotNull(message = "saleDate is required")
        @PastOrPresent(message = "saleDate cannot be in the future")
        LocalDate saleDate,

        @NotNull(message = "finalPrice is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "finalPrice must be greater than 0")
        BigDecimal finalPrice,

        @NotNull(message = "paymentMethod is required (CASH or CARD)")
        Sale.PaymentMethod paymentMethod,

        @NotNull(message = "customerId is required")
        String customerId,

        @NotNull(message = "employeeId is required")
        Long employeeId,

        @NotEmpty(message = "at least one carId is required")
        List<Long> carIds) {
}
