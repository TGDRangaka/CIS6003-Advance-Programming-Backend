package com.pahanaedu.bookshopmanagement.persistence.customerDAO;

import com.pahanaedu.bookshopmanagement.business.customer.model.Customer;
import com.pahanaedu.bookshopmanagement.persistence.SuperDAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface CustomerDAO extends SuperDAO<Customer, String> {
//    void addCustomer(Customer customer) throws SQLException;
//    Customer getCustomerById(int id) throws SQLException;
//    List<Customer> getAllCustomers() throws SQLException;
//    void updateCustomer(Customer customer) throws SQLException;
//    void deleteCustomer(int id) throws SQLException;
    void updateUnics(String accountNumber, int unitConsumed, Connection con) throws SQLException;
    boolean isAccountNumberExist(String accountNumber) throws SQLException;
}
