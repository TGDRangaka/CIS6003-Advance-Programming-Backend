package com.pahanaedu.librarymanagement.customer.model;

import com.pahanaedu.librarymanagement.schema.Entity;

import java.sql.Connection;
import java.time.LocalDateTime;

public class Customer implements Entity {
    private String accountNumber;
    private String name;
    private String email;
    private String phoneNumber;
    private int unitConsumed;
    private boolean isActive;
    private boolean isDeleted;
    private String userId;

    public Customer() {}

    public Customer(Builder builder) {
        this.accountNumber = builder.accountNumber;
        this.name = builder.name;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.unitConsumed = builder.unitConsumed;
        this.isActive = builder.isActive;
        this.isDeleted = builder.isDeleted;
        this.userId = builder.userId;
    }

    public static class Builder {
        private String accountNumber;
        private String name;
        private String email;
        private String phoneNumber;
        private int unitConsumed;
        private boolean isActive;
        private boolean isDeleted;
        private String userId;

        public Builder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder unitConsumed(int unitConsumed) {
            this.unitConsumed = unitConsumed;
            return this;
        }

        public Builder isActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder isDeleted(boolean isDeleted) {
            this.isDeleted = isDeleted;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getUnitConsumed() {
        return unitConsumed;
    }

    public void setUnitConsumed(int unitConsumed) {
        this.unitConsumed = unitConsumed;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }



    @Override
    public void createTable(Connection conn) {
        String sql = """
                    CREATE TABLE IF NOT EXISTS customer (
                        accountNumber VARCHAR(50) PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        email VARCHAR(100),
                        phoneNumber VARCHAR(20),
                        unitConsumed INT DEFAULT 0,
                        isActive BOOLEAN DEFAULT true,
                        isDeleted BOOLEAN DEFAULT false,
                        userId VARCHAR(50)
                    )
                """;
        try {
            conn.createStatement().execute(sql);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create customer table", e);
        }
    }
}
