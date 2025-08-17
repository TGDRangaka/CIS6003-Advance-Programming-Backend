package com.pahanaedu.bookshopmanagement.customer.service;

import com.pahanaedu.bookshopmanagement.customer.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAll() throws SQLException;
    CustomerDTO getById(String accountNumber) throws SQLException;
    void addCustomer(CustomerDTO customer) throws SQLException;
    void updateCustomer(CustomerDTO customer, String id) throws SQLException;
    void deleteCustomer(String accountNumber) throws SQLException;
    boolean isAccountNumberExist(String accountNumber) throws SQLException;
}
