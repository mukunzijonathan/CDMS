package com.example.CarDealership.controller;

import java.util.List;

import org.springframework.data.domain.Page;
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

import com.example.CarDealership.dto.CarRequest;
import com.example.CarDealership.dto.CarResponse;
import com.example.CarDealership.service.CarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarResponse> addCar(@Valid @RequestBody CarRequest request) {
        return new ResponseEntity<>(carService.createCar(request), HttpStatus.CREATED);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CarResponse>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping(value = "/paginated", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CarResponse>> getCarsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(carService.getAllCarsPaginated(page, size));
    }

    @GetMapping(value = "/sorted/price", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CarResponse>> getCarsSortedByPrice() {
        return ResponseEntity.ok(carService.getAllCarsSortedByPrice());
    }

    @GetMapping(value = "/sorted/year", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CarResponse>> getCarsSortedByYear() {
        return ResponseEntity.ok(carService.getAllCarsSortedByYear());
    }

    @GetMapping(value = "/getById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarResponse> getCarById(@RequestParam Long id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @GetMapping(value = "/brand", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CarResponse>> getCarsByBrand(@RequestParam String brand) {
        return ResponseEntity.ok(carService.getCarsByBrand(brand));
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarResponse> updateCar(@RequestParam Long id, @Valid @RequestBody CarRequest request) {
        return ResponseEntity.ok(carService.updateCar(id, request));
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Void> deleteCar(@RequestParam Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
