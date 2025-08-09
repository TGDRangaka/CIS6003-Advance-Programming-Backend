package com.pahanaedu.librarymanagement.customer.mapping;

import com.google.gson.Gson;
import com.pahanaedu.librarymanagement.customer.dto.CustomerDTO;
import com.pahanaedu.librarymanagement.customer.model.Customer;
import com.pahanaedu.librarymanagement.util.UtilMatters;

import java.util.ArrayList;

import java.util.List;

public class CustomerMapping {
    public static CustomerDTO toDto(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, CustomerDTO.class);
    }

    public static String toJson(CustomerDTO dto) {
        Gson gson = new Gson();
        return gson.toJson(dto);
    }

    public static String toJsonArray(List<CustomerDTO> customers) {
        Gson gson = new Gson();
        return gson.toJson(customers);
    }

    public static CustomerDTO customerToDto(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setAccountNumber(customer.getAccountNumber());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setUnitConsumed(customer.getUnitConsumed());
        dto.setActive(customer.isActive());
        return dto;
    }

    public static Customer dtoToCustomer(CustomerDTO dto) {
        return new Customer.Builder()
                .accountNumber(dto.getAccountNumber())
                .name(dto.getName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .unitConsumed(dto.getUnitConsumed())
                .isActive(dto.isActive())
                .build();
    }
}
