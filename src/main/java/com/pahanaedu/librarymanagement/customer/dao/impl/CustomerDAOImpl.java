package com.pahanaedu.librarymanagement.customer.dao.impl;

import com.pahanaedu.librarymanagement.config.DatabaseConfig;
import com.pahanaedu.librarymanagement.customer.dao.CustomerDAO;
import com.pahanaedu.librarymanagement.customer.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    private static final String ADD_CUSTOMER_SQL = "INSERT INTO customer (accountNumber, name, email, phoneNumber, unitConsumed, isActive, isDeleted, userId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_CUSTOMER_BY_ACCOUNT_SQL = "SELECT * FROM customer WHERE accountNumber = ?";
    private static final String GET_ALL_CUSTOMERS_SQL = "SELECT * FROM customer";
    private static final String UPDATE_CUSTOMER_SQL = "UPDATE customer SET name = ?, email = ?, phoneNumber = ?, unitConsumed = ?, isActive = ?, isDeleted = ?, userId = ? WHERE accountNumber = ?";
    private static final String DELETE_CUSTOMER_SQL = "DELETE FROM customer WHERE accountNumber = ?";

    @Override
    public void addCustomer(Customer customer) throws SQLException {
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(ADD_CUSTOMER_SQL)) {
            stmt.setString(1, customer.getAccountNumber());
            stmt.setString(2, customer.getName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setInt(5, customer.getUnitConsumed());
            stmt.setBoolean(6, customer.isActive());
            stmt.setBoolean(7, customer.isDeleted());
            stmt.setString(8, customer.getUserId());
            stmt.executeUpdate();
        }
    }

    @Override
    public Customer getCustomerById(int id) {
        throw new UnsupportedOperationException("Use getCustomerByAccountNumber instead");
    }

    public Customer getCustomerByAccountNumber(String accountNumber) throws SQLException {
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_CUSTOMER_BY_ACCOUNT_SQL)) {
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer.Builder()
                        .accountNumber(rs.getString("accountNumber"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .phoneNumber(rs.getString("phoneNumber"))
                        .unitConsumed(rs.getInt("unitConsumed"))
                        .isActive(rs.getBoolean("isActive"))
                        .isDeleted(rs.getBoolean("isDeleted"))
                        .userId(rs.getString("userId"))
                        .build();
            }
        }
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(GET_ALL_CUSTOMERS_SQL);
            while (rs.next()) {
                customers.add(new Customer.Builder()
                        .accountNumber(rs.getString("accountNumber"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .phoneNumber(rs.getString("phoneNumber"))
                        .unitConsumed(rs.getInt("unitConsumed"))
                        .isActive(rs.getBoolean("isActive"))
                        .isDeleted(rs.getBoolean("isDeleted"))
                        .userId(rs.getString("userId"))
                        .build());
            }
        }
        return customers;
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_CUSTOMER_SQL)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPhoneNumber());
            stmt.setInt(4, customer.getUnitConsumed());
            stmt.setBoolean(5, customer.isActive());
            stmt.setBoolean(6, customer.isDeleted());
            stmt.setString(7, customer.getUserId());
            stmt.setString(8, customer.getAccountNumber());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteCustomer(int id) {
        throw new UnsupportedOperationException("Use deleteCustomerByAccountNumber instead");
    }

    public void deleteCustomerByAccountNumber(String accountNumber) throws SQLException {
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_CUSTOMER_SQL)) {
            stmt.setString(1, accountNumber);
            stmt.executeUpdate();
        }
    }
}