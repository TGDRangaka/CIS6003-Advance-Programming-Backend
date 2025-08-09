package com.pahanaedu.librarymanagement.item.model;

import com.pahanaedu.librarymanagement.schema.Entity;

import java.sql.Connection;

public class Item implements Entity {
    private int id;
    private String name;
    private String author;
    private String category;
    private int availableCopies;

    // NoArgs constructor
    public Item() {
    }

    // AllArgs constructor
    public Item(int id, String name, String author, String category, int availableCopies) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.availableCopies = availableCopies;
    }

    // Builder pattern
    public static class Builder {
        private int id;
        private String name;
        private String author;
        private String category;
        private int availableCopies;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder availableCopies(int availableCopies) {
            this.availableCopies = availableCopies;
            return this;
        }

        public Item build() {
            return new Item(id, name, author, category, availableCopies);
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    @Override
    public void createTable(Connection conn) {
        String sql = """
                CREATE TABLE IF NOT EXISTS item (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(100) NOT NULL,
                    author VARCHAR(100),
                    category VARCHAR(50),
                    availableCopies INT DEFAULT 0
                )
                """;
        try {
            conn.createStatement().execute(sql);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create item table", e);
        }
    }
}
