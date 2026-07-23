package com.example.CarDealership.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(

        @NotBlank(message = "firstName is required")
        String firstName,

        @NotBlank(message = "lastName is required")
        String lastName,

        @NotBlank(message = "email is required")
        @Email(message = "email must be a valid address")
        String email,

        @NotBlank(message = "phoneNumber is required")
        String phoneNumber,

        @NotBlank(message = "locationId is required")
        String locationId) {
}
