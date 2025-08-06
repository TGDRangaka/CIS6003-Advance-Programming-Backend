package com.pahanaedu.librarymanagement.customer.model;

import java.time.LocalDateTime;

public class Customer {
    private String accountNumber;
    private String name;
    private String email;
    private String phoneNumber;
    private int unitConsumed;
    private boolean isActive;
    private boolean isDeleted;
    private String userId;

    public Customer() {
    }

    public Customer(String accountNumber, String name, String email, String phoneNumber, int unitConsumed, boolean isActive, boolean isDeleted, String userId) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.unitConsumed = unitConsumed;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.userId = userId;
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
}
