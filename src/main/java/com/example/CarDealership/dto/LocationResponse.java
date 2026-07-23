package com.example.CarDealership.dto;

import com.example.CarDealership.model.Location;

public record LocationResponse(
        String id,
        String name,
        String type,
        String parentId,
        String parentName) {

    public static LocationResponse from(Location location) {
        Location parent = location.getParent();
        return new LocationResponse(
                location.getId(),
                location.getName(),
                location.getType(),
                parent != null ? parent.getId() : null,
                parent != null ? parent.getName() : null);
    }
}
