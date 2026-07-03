package com.example.CarDealership.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.CarDealership.dto.LocationRequest;
import com.example.CarDealership.dto.LocationResponse;
import com.example.CarDealership.service.LocationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LocationResponse> addLocation(@Valid @RequestBody LocationRequest request) {
        return new ResponseEntity<>(locationService.createLocation(request), HttpStatus.CREATED);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LocationResponse>> getAllLocations() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }

    @GetMapping(value = "/provinces", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LocationResponse>> getAllProvinces() {
        return ResponseEntity.ok(locationService.getAllProvinces());
    }

    @GetMapping(value = "/getById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LocationResponse> getLocationById(@RequestParam String id) {
        return ResponseEntity.ok(locationService.getLocationById(id));
    }

    @GetMapping(value = "/children", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LocationResponse>> getChildren(@RequestParam String parentId) {
        return ResponseEntity.ok(locationService.getChildren(parentId));
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LocationResponse>> getByName(@RequestParam String name) {
        return ResponseEntity.ok(locationService.getByName(name));
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LocationResponse> updateLocation(@RequestParam String id,
                                                           @Valid @RequestBody LocationRequest request) {
        return ResponseEntity.ok(locationService.updateLocation(id, request));
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Void> deleteLocation(@RequestParam String id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
}
