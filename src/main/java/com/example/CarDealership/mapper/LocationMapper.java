package com.example.CarDealership.mapper;

import com.example.CarDealership.dto.LocationDTO;
import com.example.CarDealership.model.Location;

/**
 * Mapper class for Location entity ↔ LocationDTO conversion.
 * 
 * Provides static utility methods to convert between JPA entities
 * and Data Transfer Objects, cleanly separating internal domain models
 * from the API layer.
 */
public class LocationMapper {

    /**
     * Convert Location entity to LocationDTO.
     */
    public static LocationDTO toDTO(Location location) {
        if (location == null) {
            return null;
        }

        LocationDTO dto = new LocationDTO();
        dto.setId(location.getId());
        dto.setName(location.getName());
        dto.setType(location.getType());

        if (location.getParent() != null) {
            dto.setParentId(location.getParent().getId());
            dto.setParentName(location.getParent().getName());
        }

        return dto;
    }

    /**
     * Convert LocationDTO to Location entity.
     * Note: parent relationship is NOT set here; it should be handled at the service layer.
     */
    public static Location toEntity(LocationDTO dto) {
        if (dto == null) {
            return null;
        }

        Location location = new Location();
        location.setId(dto.getId());
        location.setName(dto.getName());
        location.setType(dto.getType());

        return location;
    }
}
