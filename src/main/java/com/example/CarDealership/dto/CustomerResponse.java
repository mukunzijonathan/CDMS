package com.example.CarDealership.dto;

import com.example.CarDealership.model.Customer;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        LocationResponse location) {

    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getLocation() != null ? LocationResponse.from(customer.getLocation()) : null);
    }
}
