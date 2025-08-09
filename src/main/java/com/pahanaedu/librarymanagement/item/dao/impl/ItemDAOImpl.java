package com.pahanaedu.librarymanagement.item.dao.impl;

import com.pahanaedu.librarymanagement.config.DatabaseConfig;
import com.pahanaedu.librarymanagement.item.dao.ItemDAO;
import com.pahanaedu.librarymanagement.item.model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    private static final String INSERT_ITEM_SQL = "INSERT INTO item (id, name, author, category, availableCopies) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_ITEM_SQL = "UPDATE item SET name = ?, author = ?, category = ?, availableCopies = ? WHERE id = ?";
    private static final String DELETE_ITEM_SQL = "DELETE FROM item WHERE id = ?";
    private static final String SELECT_ALL_ITEMS_SQL = "SELECT * FROM item";
    private static final String SELECT_ITEM_BY_ID_SQL = "SELECT * FROM item WHERE id = ?";

    @Override
    public void save(Item entity) throws SQLException {
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_ITEM_SQL)) {
            stmt.setInt(1, entity.getId());
            stmt.setString(2, entity.getName());
            stmt.setString(3, entity.getAuthor());
            stmt.setString(4, entity.getCategory());
            stmt.setInt(5, entity.getAvailableCopies());
            stmt.executeUpdate();
        }
    }

    @Override
    public void update(Item entity, Integer id) throws SQLException {
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_ITEM_SQL)) {
            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getAuthor());
            stmt.setString(3, entity.getCategory());
            stmt.setInt(4, entity.getAvailableCopies());
            stmt.setInt(5, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_ITEM_SQL)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Item> getAll() throws SQLException {
        List<Item> items = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SELECT_ALL_ITEMS_SQL);
            while (rs.next()) {
                items.add(new Item.Builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .author(rs.getString("author"))
                        .category(rs.getString("category"))
                        .availableCopies(rs.getInt("availableCopies"))
                        .build()
                );
            }
        }
        return items;
    }

    @Override
    public Item getById(Integer id) throws SQLException {
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ITEM_BY_ID_SQL)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Item.Builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .author(rs.getString("author"))
                        .category(rs.getString("category"))
                        .availableCopies(rs.getInt("availableCopies"))
                        .build();
            }
        }
        return null;
    }
}
