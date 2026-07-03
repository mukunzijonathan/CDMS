package com.example.CarDealership.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.CarDealership.dto.SaleRequest;
import com.example.CarDealership.dto.SaleResponse;
import com.example.CarDealership.exception.ResourceNotFoundException;
import com.example.CarDealership.model.Car;
import com.example.CarDealership.model.Customer;
import com.example.CarDealership.model.Employee;
import com.example.CarDealership.model.Sale;
import com.example.CarDealership.repository.CarRepository;
import com.example.CarDealership.repository.CustomerRepository;
import com.example.CarDealership.repository.EmployeeRepository;
import com.example.CarDealership.repository.SaleRepository;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final CarRepository carRepository;

    public SaleService(SaleRepository saleRepository,
                       CustomerRepository customerRepository,
                       EmployeeRepository employeeRepository,
                       CarRepository carRepository) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.carRepository = carRepository;
    }

    public SaleResponse createSale(SaleRequest request) {
        Sale sale = new Sale();
        apply(sale, request);
        return SaleResponse.from(saleRepository.save(sale));
    }

    public List<SaleResponse> getAllSales() {
        return saleRepository.findAll().stream().map(SaleResponse::from).toList();
    }

    public SaleResponse getSaleById(Long id) {
        return SaleResponse.from(findOrThrow(id));
    }

    public List<SaleResponse> getSalesByCustomer(String customerId) {
        return saleRepository.findByCustomer_Id(customerId).stream().map(SaleResponse::from).toList();
    }

    public List<SaleResponse> getSalesByEmployee(Long employeeId) {
        return saleRepository.findByEmployee_Id(employeeId).stream().map(SaleResponse::from).toList();
    }

    public List<SaleResponse> getSalesByPaymentMethod(Sale.PaymentMethod paymentMethod) {
        return saleRepository.findByPaymentMethod(paymentMethod).stream().map(SaleResponse::from).toList();
    }

    public SaleResponse updateSale(Long id, SaleRequest request) {
        Sale sale = findOrThrow(id);
        apply(sale, request);
        return SaleResponse.from(saleRepository.save(sale));
    }

    public void deleteSale(Long id) {
        if (!saleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sale", id);
        }
        saleRepository.deleteById(id);
    }

    private Sale findOrThrow(Long id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale", id));
    }

    private void apply(Sale sale, SaleRequest request) {
        sale.setSaleDate(request.saleDate());
        sale.setFinalPrice(request.finalPrice());
        sale.setPaymentMethod(request.paymentMethod());
        sale.setCustomer(resolveCustomer(request.customerId()));
        sale.setEmployee(resolveEmployee(request.employeeId()));
        sale.setCars(resolveCars(request.carIds()));
    }

    private Customer resolveCustomer(String customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", customerId));
    }

    private Employee resolveEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", employeeId));
    }

    private List<Car> resolveCars(List<Long> carIds) {
        List<Car> cars = new ArrayList<>();
        for (Long carId : carIds) {
            cars.add(carRepository.findById(carId)
                    .orElseThrow(() -> new ResourceNotFoundException("Car", carId)));
        }
        return cars;
    }
}
