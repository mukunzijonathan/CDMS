package com.example.CarDealership.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.CarDealership.dto.LocationRequest;
import com.example.CarDealership.dto.LocationResponse;
import com.example.CarDealership.exception.DuplicateResourceException;
import com.example.CarDealership.exception.ResourceNotFoundException;
import com.example.CarDealership.model.Location;
import com.example.CarDealership.repository.LocationRepository;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public LocationResponse createLocation(LocationRequest request) {
        if (locationRepository.existsByNameAndType(request.name(), request.type())) {
            throw new DuplicateResourceException(request.type() + " '" + request.name() + "' already exists.");
        }
        Location location = new Location();
        location.setName(request.name());
        location.setType(request.type());
        location.setParent(resolveParent(request.parentId()));
        return LocationResponse.from(locationRepository.save(location));
    }

    public List<LocationResponse> getAllLocations() {
        return locationRepository.findAll().stream().map(LocationResponse::from).toList();
    }

    public LocationResponse getLocationById(String id) {
        return LocationResponse.from(findOrThrow(id));
    }

    public List<LocationResponse> getAllProvinces() {
        return locationRepository.findByType("PROVINCE").stream().map(LocationResponse::from).toList();
    }

    public List<LocationResponse> getChildren(String parentId) {
        return locationRepository.findByParent_Id(parentId).stream().map(LocationResponse::from).toList();
    }

    public List<LocationResponse> getByName(String name) {
        return locationRepository.findByName(name).stream().map(LocationResponse::from).toList();
    }

    public LocationResponse updateLocation(String id, LocationRequest request) {
        Location location = findOrThrow(id);
        location.setName(request.name());
        location.setType(request.type());
        location.setParent(resolveParent(request.parentId()));
        return LocationResponse.from(locationRepository.save(location));
    }

    public void deleteLocation(String id) {
        if (!locationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Location", id);
        }
        locationRepository.deleteById(id);
    }

    private Location findOrThrow(String id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location", id));
    }

    private Location resolveParent(String parentId) {
        if (parentId == null || parentId.isBlank()) {
            return null;
        }
        return locationRepository.findById(parentId)
                .orElseThrow(() -> new ResourceNotFoundException("Parent location", parentId));
    }
}
