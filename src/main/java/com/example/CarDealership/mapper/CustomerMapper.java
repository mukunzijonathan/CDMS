package com.example.CarDealership.mapper;

import com.example.CarDealership.dto.CustomerDTO;
import com.example.CarDealership.model.Customer;

/**
 * Mapper class for Customer entity ↔ CustomerDTO conversion.
 */
public class CustomerMapper {

    /**
     * Convert Customer entity to CustomerDTO.
     */
    public static CustomerDTO toDTO(Customer customer) {
        if (customer == null) {
            return null;
        }

        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setEmail(customer.getEmail());
        dto.setPhoneNumber(customer.getPhoneNumber());

        if (customer.getLocation() != null) {
            dto.setLocationId(customer.getLocation().getId());
            dto.setLocationName(customer.getLocation().getName());
        }

        return dto;
    }

    /**
     * Convert CustomerDTO to Customer entity.
     * Note: location relationship must be resolved at the service layer.
     */
    public static Customer toEntity(CustomerDTO dto) {
        if (dto == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());
        customer.setPhoneNumber(dto.getPhoneNumber());

        return customer;
    }

    /**
     * Update existing Customer entity with DTO values.
     * Useful for PUT requests.
     */
    public static void updateEntityFromDTO(CustomerDTO dto, Customer customer) {
        if (dto == null || customer == null) {
            return;
        }

        if (dto.getFirstName() != null) {
            customer.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            customer.setLastName(dto.getLastName());
        }
        if (dto.getEmail() != null) {
            customer.setEmail(dto.getEmail());
        }
        if (dto.getPhoneNumber() != null) {
            customer.setPhoneNumber(dto.getPhoneNumber());
        }
    }
}
