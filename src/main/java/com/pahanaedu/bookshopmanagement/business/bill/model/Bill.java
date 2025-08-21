package com.pahanaedu.bookshopmanagement.business.bill.model;

import com.pahanaedu.bookshopmanagement.schema.Entity;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class Bill implements Entity {
    private String billId;
    private String accountNumber;
    private String billDate;
    private double total;
    private List<BillItem> items;

    // No-args constructor
    public Bill() {
    }

    // All-args constructor
    public Bill(String billId, String accountNumber, String billDate, double total, List<BillItem> items) {
        this.billId = billId;
        this.accountNumber = accountNumber;
        this.billDate = billDate;
        this.total = total;
        this.items = items;
    }

    // Builder pattern
    public static class Builder {
        private String billId;
        private String accountNumber;
        private String billDate;
        private double total;
        private List<BillItem> items;

        public Builder billId(String billId) {
            this.billId = billId;
            return this;
        }

        public Builder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder billDate(String billDate) {
            this.billDate = billDate;
            return this;
        }

        public Builder total(double total) {
            this.total = total;
            return this;
        }

        public Builder items(List<BillItem> items) {
            this.items = items;
            return this;
        }

        public Builder item(BillItem item) {
            this.items.add(item);
            return this;
        }

        public Bill build() {
            return new Bill(billId, accountNumber, billDate, total, items);
        }
    }

    // Getters and setters
    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<BillItem> getItems() {
        return items;
    }

    public void setItems(List<BillItem> items) {
        this.items = items;
    }

    @Override
    public void createTable(Connection conn) {
        String sql = """
                CREATE TABLE IF NOT EXISTS bill (
                    bill_id VARCHAR(255) PRIMARY KEY UNIQUE,
                    accountNumber VARCHAR(255) NOT NULL,
                    bill_date TIMESTAMP,
                    total DOUBLE NOT NULL,
                    FOREIGN KEY (accountNumber) REFERENCES customer(accountNumber) ON DELETE CASCADE
                )
                """;
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create bill table", e);
        }
    }
}
