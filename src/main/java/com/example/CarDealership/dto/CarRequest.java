package com.example.CarDealership.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CarRequest(

        @NotBlank(message = "brand is required")
        String brand,

        @NotBlank(message = "model is required")
        String model,

        @Min(value = 1900, message = "year must be 1900 or later")
        @Max(value = 2100, message = "year must be 2100 or earlier")
        int year,

        @NotNull(message = "price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "price must be greater than 0")
        BigDecimal price) {
}
