package com.example.CarDealership.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.CarDealership.dto.CarRequest;
import com.example.CarDealership.dto.CarResponse;
import com.example.CarDealership.exception.DuplicateResourceException;
import com.example.CarDealership.exception.ResourceNotFoundException;
import com.example.CarDealership.model.Car;
import com.example.CarDealership.repository.CarRepository;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarResponse createCar(CarRequest request) {
        if (carRepository.existsByBrandAndModelAndYear(
                request.brand(), request.model(), request.year())) {
            throw new DuplicateResourceException("Car already exists with the same brand, model and year.");
        }
        Car car = new Car();
        apply(car, request);
        return CarResponse.from(carRepository.save(car));
    }

    public List<CarResponse> getAllCars() {
        return carRepository.findAll().stream().map(CarResponse::from).toList();
    }

    public Page<CarResponse> getAllCarsPaginated(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return carRepository.findAll(pageable).map(CarResponse::from);
    }

    public List<CarResponse> getAllCarsSortedByPrice() {
        return carRepository.findAll(Sort.by(Sort.Direction.ASC, "price"))
                .stream().map(CarResponse::from).toList();
    }

    public List<CarResponse> getAllCarsSortedByYear() {
        return carRepository.findAll(Sort.by(Sort.Direction.DESC, "year"))
                .stream().map(CarResponse::from).toList();
    }

    public CarResponse getCarById(Long id) {
        return CarResponse.from(findOrThrow(id));
    }

    public List<CarResponse> getCarsByBrand(String brand) {
        return carRepository.findByBrand(brand).stream().map(CarResponse::from).toList();
    }

    public CarResponse updateCar(Long id, CarRequest request) {
        Car car = findOrThrow(id);
        apply(car, request);
        return CarResponse.from(carRepository.save(car));
    }

    public void deleteCar(Long id) {
        if (!carRepository.existsById(id)) {
            throw new ResourceNotFoundException("Car", id);
        }
        carRepository.deleteById(id);
    }

    private Car findOrThrow(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car", id));
    }

    private void apply(Car car, CarRequest request) {
        car.setBrand(request.brand());
        car.setModel(request.model());
        car.setYear(request.year());
        car.setPrice(request.price());
    }
}
