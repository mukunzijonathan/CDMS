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

import com.example.CarDealership.dto.EmployeeRequest;
import com.example.CarDealership.dto.EmployeeResponse;
import com.example.CarDealership.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeResponse> addEmployee(@Valid @RequestBody EmployeeRequest request) {
        return new ResponseEntity<>(employeeService.createEmployee(request), HttpStatus.CREATED);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping(value = "/getById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeResponse> getEmployeeById(@RequestParam Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping(value = "/location", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeResponse>> getByLocationName(@RequestParam String name) {
        return ResponseEntity.ok(employeeService.getEmployeesByLocationName(name));
    }

    @GetMapping(value = "/province", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeResponse>> getByProvince(@RequestParam String name) {
        return ResponseEntity.ok(employeeService.getEmployeesByProvince(name));
    }

    @GetMapping(value = "/provinceId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeResponse>> getByProvinceId(@RequestParam String id) {
        return ResponseEntity.ok(employeeService.getEmployeesByProvinceId(id));
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeResponse> updateEmployee(@RequestParam Long id,
                                                           @Valid @RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, request));
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Void> deleteEmployee(@RequestParam Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
