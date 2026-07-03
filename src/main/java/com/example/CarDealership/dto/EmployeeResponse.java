package com.example.CarDealership.dto;

import com.example.CarDealership.model.Employee;

public record EmployeeResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        LocationResponse location) {

    public static EmployeeResponse from(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getLocation() != null ? LocationResponse.from(employee.getLocation()) : null);
    }
}
