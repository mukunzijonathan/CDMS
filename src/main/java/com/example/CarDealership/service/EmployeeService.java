package com.example.CarDealership.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.CarDealership.dto.EmployeeRequest;
import com.example.CarDealership.dto.EmployeeResponse;
import com.example.CarDealership.exception.DuplicateResourceException;
import com.example.CarDealership.exception.ResourceNotFoundException;
import com.example.CarDealership.model.Employee;
import com.example.CarDealership.model.Location;
import com.example.CarDealership.repository.EmployeeRepository;
import com.example.CarDealership.repository.LocationRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final LocationRepository locationRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           LocationRepository locationRepository) {
        this.employeeRepository = employeeRepository;
        this.locationRepository = locationRepository;
    }

    public EmployeeResponse createEmployee(EmployeeRequest request) {
        if (employeeRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("An employee with email '" + request.email() + "' already exists.");
        }
        Employee employee = new Employee();
        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setEmail(request.email());
        employee.setLocation(resolveLocation(request.locationId()));
        return EmployeeResponse.from(employeeRepository.save(employee));
    }

    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll().stream().map(EmployeeResponse::from).toList();
    }

    public EmployeeResponse getEmployeeById(Long id) {
        return EmployeeResponse.from(findOrThrow(id));
    }

    public List<EmployeeResponse> getEmployeesByLocationName(String name) {
        return employeeRepository.findByLocation_Name(name).stream().map(EmployeeResponse::from).toList();
    }

    public List<EmployeeResponse> getEmployeesByProvince(String provinceName) {
        return employeeRepository.findByProvinceName(provinceName).stream().map(EmployeeResponse::from).toList();
    }

    public List<EmployeeResponse> getEmployeesByProvinceId(String id) {
        return employeeRepository.findByProvinceId(id).stream().map(EmployeeResponse::from).toList();
    }

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {
        Employee employee = findOrThrow(id);
        if (!employee.getEmail().equals(request.email())
                && employeeRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("An employee with email '" + request.email() + "' already exists.");
        }
        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setEmail(request.email());
        employee.setLocation(resolveLocation(request.locationId()));
        return EmployeeResponse.from(employeeRepository.save(employee));
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee", id);
        }
        employeeRepository.deleteById(id);
    }

    private Employee findOrThrow(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", id));
    }

    private Location resolveLocation(String locationId) {
        return locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location", locationId));
    }
}
