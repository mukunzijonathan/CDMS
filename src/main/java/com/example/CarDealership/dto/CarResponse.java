package com.example.CarDealership.dto;

import java.math.BigDecimal;

import com.example.CarDealership.model.Car;

public record CarResponse(
        Long id,
        String brand,
        String model,
        int year,
        BigDecimal price) {

    public static CarResponse from(Car car) {
        return new CarResponse(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getYear(),
                car.getPrice());
    }
}
