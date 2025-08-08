package com.pahanaedu.librarymanagement.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String DB_NAME = "library_management";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";

    private static final DatabaseConfig instance = new DatabaseConfig();

    private DatabaseConfig() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // create database if not exist to avoid exceptions
            Connection tempConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", DB_USER, DB_PASSWORD);
            tempConnection.createStatement().executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            tempConnection.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error initializing database!", e);
        }
    }

    public static DatabaseConfig getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
