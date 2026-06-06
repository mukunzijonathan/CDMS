package com.example.CarDealership.mapper;

import com.example.CarDealership.dto.CarDTO;
import com.example.CarDealership.model.Car;

/**
 * Mapper class for Car entity ↔ CarDTO conversion.
 */
public class CarMapper {

    /**
     * Convert Car entity to CarDTO.
     */
    public static CarDTO toDTO(Car car) {
        if (car == null) {
            return null;
        }

        return new CarDTO(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getYear(),
                car.getPrice()
        );
    }

    /**
     * Convert CarDTO to Car entity.
     */
    public static Car toEntity(CarDTO dto) {
        if (dto == null) {
            return null;
        }

        Car car = new Car();
        if (dto.getId() != null) {
            car.setId(dto.getId());
        }
        car.setBrand(dto.getBrand());
        car.setModel(dto.getModel());
        car.setYear(dto.getYear());
        car.setPrice(dto.getPrice());

        return car;
    }

    /**
     * Update existing Car entity with DTO values.
     */
    public static void updateEntityFromDTO(CarDTO dto, Car car) {
        if (dto == null || car == null) {
            return;
        }

        if (dto.getBrand() != null) {
            car.setBrand(dto.getBrand());
        }
        if (dto.getModel() != null) {
            car.setModel(dto.getModel());
        }
        if (dto.getYear() > 0) {
            car.setYear(dto.getYear());
        }
        if (dto.getPrice() > 0) {
            car.setPrice(dto.getPrice());
        }
    }
}
