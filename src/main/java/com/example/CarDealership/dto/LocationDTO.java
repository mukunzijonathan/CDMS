package com.example.CarDealership.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for Location.
 * 
 * Used to transfer location data via the API. Hides internal database structure
 * and provides validation at the API boundary.
 */
@Schema(description = "Location Data Transfer Object")
public class LocationDTO {

    @Schema(description = "Unique identifier", example = "550e8400-e29b-41d4-a716-446655440000")
    private String id;

    @Schema(description = "Location name", example = "Kigali City")
    @NotBlank(message = "Location name is required")
    @Size(min = 2, max = 100, message = "Location name must be between 2 and 100 characters")
    private String name;

    @Schema(description = "Location type in hierarchy", example = "PROVINCE", allowableValues = {"PROVINCE", "DISTRICT", "SECTOR", "CELL", "VILLAGE"})
    @NotBlank(message = "Location type is required")
    private String type;

    @Schema(description = "Parent location ID (null for provinces)", example = "550e8400-e29b-41d4-a716-446655440001")
    private String parentId;

    @Schema(description = "Parent location name", example = "Rwanda")
    private String parentName;

    // Constructors
    public LocationDTO() {}

    public LocationDTO(String id, String name, String type, String parentId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.parentId = parentId;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getParentId() { return parentId; }
    public void setParentId(String parentId) { this.parentId = parentId; }

    public String getParentName() { return parentName; }
    public void setParentName(String parentName) { this.parentName = parentName; }
}
