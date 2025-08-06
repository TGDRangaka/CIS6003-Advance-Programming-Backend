package com.pahanaedu.librarymanagement.customer.mapping;

import com.pahanaedu.librarymanagement.customer.dto.CustomerDTO;
import com.pahanaedu.librarymanagement.util.UtilMatters;

public class CustomerMapping {
    public static CustomerDTO jsonToDto(String json){
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
}
