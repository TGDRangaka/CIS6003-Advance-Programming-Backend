package com.pahanaedu.librarymanagement.customer.mapping;

import com.pahanaedu.librarymanagement.customer.dto.CustomerDTO;
import com.pahanaedu.librarymanagement.customer.model.Customer;
import com.pahanaedu.librarymanagement.util.UtilMatters;

public class CustomerMapping {
    public static CustomerDTO jsonToDto(String json) {
        CustomerDTO dto = new CustomerDTO();

        dto.setAccountNumber(UtilMatters.getJsonValue(json, "accountNumber"));
        dto.setName(UtilMatters.getJsonValue(json, "name"));
        dto.setEmail(UtilMatters.getJsonValue(json, "email"));
        dto.setPhoneNumber(UtilMatters.getJsonValue(json, "phoneNumber"));
        String unitConsumedStr = UtilMatters.getJsonValue(json, "unitConsumed");
        String isActiveStr = UtilMatters.getJsonValue(json, "isActive");

        dto.setUnitConsumed(unitConsumedStr.isEmpty() ? 0 : Integer.parseInt(unitConsumedStr));
        dto.setActive(isActiveStr.equalsIgnoreCase("true"));

        return dto;
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
