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

import com.example.CarDealership.dto.SaleRequest;
import com.example.CarDealership.dto.SaleResponse;
import com.example.CarDealership.model.Sale;
import com.example.CarDealership.service.SaleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SaleResponse> addSale(@Valid @RequestBody SaleRequest request) {
        return new ResponseEntity<>(saleService.createSale(request), HttpStatus.CREATED);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SaleResponse>> getAllSales() {
        return ResponseEntity.ok(saleService.getAllSales());
    }

    @GetMapping(value = "/getById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SaleResponse> getSaleById(@RequestParam Long id) {
        return ResponseEntity.ok(saleService.getSaleById(id));
    }

    @GetMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SaleResponse>> getSalesByCustomer(@RequestParam String customerId) {
        return ResponseEntity.ok(saleService.getSalesByCustomer(customerId));
    }

    @GetMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SaleResponse>> getSalesByEmployee(@RequestParam Long employeeId) {
        return ResponseEntity.ok(saleService.getSalesByEmployee(employeeId));
    }

    @GetMapping(value = "/payment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SaleResponse>> getSalesByPaymentMethod(@RequestParam Sale.PaymentMethod paymentMethod) {
        return ResponseEntity.ok(saleService.getSalesByPaymentMethod(paymentMethod));
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SaleResponse> updateSale(@RequestParam Long id, @Valid @RequestBody SaleRequest request) {
        return ResponseEntity.ok(saleService.updateSale(id, request));
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Void> deleteSale(@RequestParam Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}
