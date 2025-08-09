package com.pahanaedu.librarymanagement.customer.service;

import com.pahanaedu.librarymanagement.customer.dto.CustomerDTO;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAll() throws SQLException;
    CustomerDTO getById(String accountNumber) throws SQLException;
    void addCustomer(CustomerDTO customer) throws SQLException;
    void updateCustomer(CustomerDTO customer, String id) throws SQLException;
    void deleteCustomer(String accountNumber) throws SQLException;
}
