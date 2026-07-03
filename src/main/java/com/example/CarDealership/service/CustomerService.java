package com.example.CarDealership.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.CarDealership.dto.CustomerRequest;
import com.example.CarDealership.dto.CustomerResponse;
import com.example.CarDealership.exception.DuplicateResourceException;
import com.example.CarDealership.exception.ResourceNotFoundException;
import com.example.CarDealership.model.Customer;
import com.example.CarDealership.model.Location;
import com.example.CarDealership.repository.CustomerRepository;
import com.example.CarDealership.repository.LocationRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final LocationRepository locationRepository;

    public CustomerService(CustomerRepository customerRepository,
                           LocationRepository locationRepository) {
        this.customerRepository = customerRepository;
        this.locationRepository = locationRepository;
    }

    public CustomerResponse createCustomer(CustomerRequest request) {
        if (customerRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("A customer with email '" + request.email() + "' already exists.");
        }
        Customer customer = new Customer();
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());
        customer.setPhoneNumber(request.phoneNumber());
        customer.setLocation(resolveLocation(request.locationId()));
        return CustomerResponse.from(customerRepository.save(customer));
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream().map(CustomerResponse::from).toList();
    }

    public CustomerResponse getCustomerById(String id) {
        return CustomerResponse.from(findOrThrow(id));
    }

    public List<CustomerResponse> getCustomersByLocationName(String name) {
        return customerRepository.findByLocation_Name(name).stream().map(CustomerResponse::from).toList();
    }

    public List<CustomerResponse> getCustomersByProvince(String provinceName) {
        return customerRepository.findByProvinceName(provinceName).stream().map(CustomerResponse::from).toList();
    }

    public List<CustomerResponse> getCustomersByProvinceId(String id) {
        return customerRepository.findByProvinceId(id).stream().map(CustomerResponse::from).toList();
    }

    public CustomerResponse updateCustomer(String id, CustomerRequest request) {
        Customer customer = findOrThrow(id);
        if (!customer.getEmail().equals(request.email())
                && customerRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("A customer with email '" + request.email() + "' already exists.");
        }
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());
        customer.setPhoneNumber(request.phoneNumber());
        customer.setLocation(resolveLocation(request.locationId()));
        return CustomerResponse.from(customerRepository.save(customer));
    }

    public void deleteCustomer(String id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer", id);
        }
        customerRepository.deleteById(id);
    }

    private Customer findOrThrow(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", id));
    }

    private Location resolveLocation(String locationId) {
        return locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location", locationId));
    }
}
