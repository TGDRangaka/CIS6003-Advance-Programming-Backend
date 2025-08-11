package com.pahanaedu.bookshopmanagement.item.model;

import com.pahanaedu.bookshopmanagement.schema.Entity;

import java.sql.Connection;

public class Item implements Entity {
    private int id;
    private String name;
    private String category;
    private int qty;
    private double price;

    // NoArgs constructor
    public Item() {
    }

    // AllArgs constructor
    public Item(int id, String name, String category, int qty, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.qty = qty;
        this.price = price;
    }

    // Builder pattern
    public static class Builder {
        private int id;
        private String name;
        private String category;
        private int qty;
        private double price;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
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

        public Item build() {
            return new Item(id, name, category, qty, price);
        }
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
                CREATE TABLE IF NOT EXISTS item (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(100) NOT NULL,
                    category VARCHAR(50),
                    qty INT DEFAULT 0,
                    price DOUBLE DEFAULT 0.0
                )
                """;
        try {
            conn.createStatement().execute(sql);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create item table", e);
        }
    }
}
