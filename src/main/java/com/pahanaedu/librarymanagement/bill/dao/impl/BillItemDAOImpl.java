package com.pahanaedu.librarymanagement.bill.dao.impl;

import com.pahanaedu.librarymanagement.bill.dao.BillItemDAO;
import com.pahanaedu.librarymanagement.bill.model.BillItem;
import com.pahanaedu.librarymanagement.config.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillItemDAOImpl implements BillItemDAO {

    private static final String INSERT_SQL = "INSERT INTO bill_item (bill_item_id, bill_id, item_id, qty, price) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE bill_item SET bill_id = ?, item_id = ?, qty = ?, price = ? WHERE bill_item_id = ?";
    private static final String DELETE_SQL = "DELETE FROM bill_item WHERE bill_item_id = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM bill_item";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM bill_item WHERE bill_item_id = ?";

    @Override
    public void save(BillItem entity) throws SQLException {
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {
            stmt.setString(1, entity.getBillItemId());
            stmt.setString(2, entity.getBillId());
            stmt.setString(3, entity.getItemId());
            stmt.setInt(4, entity.getQty());
            stmt.setDouble(5, entity.getPrice());
            stmt.executeUpdate();
        }
    }

    @Override
    public void update(BillItem entity, String id) throws SQLException {
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL)) {
            stmt.setString(1, entity.getBillId());
            stmt.setString(2, entity.getItemId());
            stmt.setInt(3, entity.getQty());
            stmt.setDouble(4, entity.getPrice());
            stmt.setString(5, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(String id) throws SQLException {
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<BillItem> getAll() throws SQLException {
        List<BillItem> billItems = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL)) {

            while (rs.next()) {
                billItems.add(new BillItem.Builder()
                        .billItemId(rs.getString("bill_item_id"))
                        .billId(rs.getString("bill_id"))
                        .itemId(rs.getString("item_id"))
                        .qty(rs.getInt("qty"))
                        .price(rs.getDouble("price"))
                        .build());
            }
        }
        return billItems;
    }

    @Override
    public BillItem getById(String id) throws SQLException {
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new BillItem.Builder()
                        .billItemId(rs.getString("bill_item_id"))
                        .billId(rs.getString("bill_id"))
                        .itemId(rs.getString("item_id"))
                        .qty(rs.getInt("qty"))
                        .price(rs.getDouble("price"))
                        .build();
            }
        }
        return null;
    }

    @Override
    public List<BillItem> getBillItemsByBillId(String billId) throws SQLException {
        return List.of();
    }
}
