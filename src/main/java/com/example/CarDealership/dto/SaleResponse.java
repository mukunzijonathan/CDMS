package com.example.CarDealership.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.example.CarDealership.model.Sale;

public record SaleResponse(
        Long id,
        LocalDate saleDate,
        BigDecimal finalPrice,
        Sale.PaymentMethod paymentMethod,
        CustomerResponse customer,
        EmployeeResponse employee,
        List<CarResponse> cars) {

    public static SaleResponse from(Sale sale) {
        return new SaleResponse(
                sale.getId(),
                sale.getSaleDate(),
                sale.getFinalPrice(),
                sale.getPaymentMethod(),
                sale.getCustomer() != null ? CustomerResponse.from(sale.getCustomer()) : null,
                sale.getEmployee() != null ? EmployeeResponse.from(sale.getEmployee()) : null,
                sale.getCars() != null
                        ? sale.getCars().stream().map(CarResponse::from).toList()
                        : List.of());
    }
}
