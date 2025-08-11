package com.pahanaedu.librarymanagement.bill.model;

import com.pahanaedu.librarymanagement.schema.Entity;

import java.sql.Connection;
import java.sql.Statement;

public class BillItem implements Entity {
    private String billItemId;
    private String billId;
    private String itemId;
    private int qty;
    private double price;

    // No-args constructor
    public BillItem() {
    }

    // All-args constructor
    public BillItem(String billItemId, String billId, String itemId, int qty, double price) {
        this.billItemId = billItemId;
        this.billId = billId;
        this.itemId = itemId;
        this.qty = qty;
        this.price = price;
    }

    // Builder pattern
    public static class Builder {
        private String billItemId;
        private String billId;
        private String itemId;
        private int qty;
        private double price;

        public Builder billItemId(String billItemId) {
            this.billItemId = billItemId;
            return this;
        }

        public Builder billId(String billId) {
            this.billId = billId;
            return this;
        }

        public Builder itemId(String itemId) {
            this.itemId = itemId;
            return this;
        }

        public Builder qty(int qty) {
            this.qty = qty;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public BillItem build() {
            return new BillItem(billItemId, billId, itemId, qty, price);
        }
    }

    // Getters and setters
    public String getBillItemId() {
        return billItemId;
    }

    public void setBillItemId(String billItemId) {
        this.billItemId = billItemId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void createTable(Connection conn) {
        String sql = """
                CREATE TABLE IF NOT EXISTS bill_item (
                    bill_item_id VARCHAR(255) PRIMARY KEY UNIQUE,
                    bill_id VARCHAR(255) NOT NULL,
                    item_id INT NOT NULL,
                    qty INT NOT NULL,
                    price DOUBLE NOT NULL,
                    FOREIGN KEY (bill_id) REFERENCES bill(bill_id) ON DELETE CASCADE,
                    FOREIGN KEY (item_id) REFERENCES item(id) ON DELETE CASCADE
                )
                """;
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create bill_item table", e);
        }
    }
}
