package com.example.CarDealership.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LocationRequest(

        @NotBlank(message = "name is required")
        String name,

        @NotBlank(message = "type is required")
        @Pattern(regexp = "PROVINCE|DISTRICT|SECTOR|CELL|VILLAGE",
                message = "type must be one of PROVINCE, DISTRICT, SECTOR, CELL, VILLAGE")
        String type,

        // Optional — null for a PROVINCE (top of the tree)
        String parentId) {
}
