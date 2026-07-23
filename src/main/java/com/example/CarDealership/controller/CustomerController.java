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

import com.example.CarDealership.dto.CustomerRequest;
import com.example.CarDealership.dto.CustomerResponse;
import com.example.CarDealership.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponse> addCustomer(@Valid @RequestBody CustomerRequest request) {
        return new ResponseEntity<>(customerService.createCustomer(request), HttpStatus.CREATED);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping(value = "/getById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponse> getCustomerById(@RequestParam String id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping(value = "/location", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerResponse>> getByLocationName(@RequestParam String name) {
        return ResponseEntity.ok(customerService.getCustomersByLocationName(name));
    }

    @GetMapping(value = "/province", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerResponse>> getByProvince(@RequestParam String name) {
        return ResponseEntity.ok(customerService.getCustomersByProvince(name));
    }

    @GetMapping(value = "/provinceId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerResponse>> getByProvinceId(@RequestParam String id) {
        return ResponseEntity.ok(customerService.getCustomersByProvinceId(id));
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestParam String id,
                                                           @Valid @RequestBody CustomerRequest request) {
        return ResponseEntity.ok(customerService.updateCustomer(id, request));
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Void> deleteCustomer(@RequestParam String id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
