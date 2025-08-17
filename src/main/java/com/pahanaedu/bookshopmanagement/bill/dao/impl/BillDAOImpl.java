package com.pahanaedu.bookshopmanagement.bill.dao.impl;

import com.pahanaedu.bookshopmanagement.bill.dao.BillDAO;
import com.pahanaedu.bookshopmanagement.bill.model.Bill;
import com.pahanaedu.bookshopmanagement.bill.model.BillItem;
import com.pahanaedu.bookshopmanagement.config.DatabaseConfig;
import com.pahanaedu.bookshopmanagement.customer.dao.CustomerDAO;
import com.pahanaedu.bookshopmanagement.customer.dao.impl.CustomerDAOImpl;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillDAOImpl implements BillDAO {
    private final CustomerDAO customerDAO;

    private static final String INSERT_BILL_SQL = "INSERT INTO bill (bill_id, accountNumber, bill_date, total) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_BILL_SQL = "UPDATE bill SET accountNumber = ?, bill_date = ?, total = ? WHERE bill_id = ?";
    private static final String DELETE_BILL_SQL = "DELETE FROM bill WHERE bill_id = ?";
    private static final String SELECT_BILLS_WITH_ITEMS_SQL = """
            SELECT
                b.bill_id, b.accountNumber, b.bill_date, b.total,
                bi.bill_item_id, bi.item_id, bi.qty, bi.price
            FROM bill b
            LEFT JOIN bill_item bi ON b.bill_id = bi.bill_id
            ORDER BY b.bill_id
            """;
    private static final String SELECT_BILL_WITH_ITEMS_BY_ID_SQL = """
            SELECT
                b.bill_id, b.accountNumber, b.bill_date, b.total,
                bi.bill_item_id, bi.item_id, bi.qty, bi.price
            FROM bill b
            LEFT JOIN bill_item bi ON b.bill_id = bi.bill_id
            WHERE b.bill_id = ?
            """;

    private static final String INSERT_BILL_ITEM_SQL = "INSERT INTO bill_item (bill_item_id, bill_id, item_id, qty, price) VALUES (?, ?, ?, ?, ?)";


    public BillDAOImpl() {
        this.customerDAO = new CustomerDAOImpl();
    }

    @Override
    public void save(Bill entity) throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseConfig.getInstance().getConnection();
            conn.setAutoCommit(false); // Begin transaction

            LocalDateTime ldt = LocalDateTime.parse(entity.getBillDate());

            // Insert Bill
            try (PreparedStatement billStmt = conn.prepareStatement(INSERT_BILL_SQL)) {
                billStmt.setString(1, entity.getBillId());
                billStmt.setString(2, entity.getAccountNumber());
                billStmt.setTimestamp(3, Timestamp.valueOf(ldt));
                billStmt.setDouble(4, entity.getTotal());
                billStmt.executeUpdate();
            }

            // Insert Bill Items
            if (entity.getItems() != null) {
                try (PreparedStatement itemStmt = conn.prepareStatement(INSERT_BILL_ITEM_SQL)) {
                    for (BillItem item : entity.getItems()) {
                        itemStmt.setString(1, item.getBillItemId());
                        itemStmt.setString(2, entity.getBillId());
                        itemStmt.setString(3, item.getItemId());
                        itemStmt.setInt(4, item.getQty());
                        itemStmt.setDouble(5, item.getPrice());
                        itemStmt.addBatch();
                    }
                    itemStmt.executeBatch();
                }
            }

            // Update customer unit consumed if applicable
            if (entity.getAccountNumber() != null && entity.getItems() != null) {
                int totalQty = entity.getItems().stream().mapToInt(BillItem::getQty).sum();
                customerDAO.updateUnics(entity.getAccountNumber(), totalQty, conn);
            } else {
                throw new SQLException("Bill must have an account number and items to update customer unit consumed.");
            }

            conn.commit(); // Commit transaction
        } catch (SQLException e) {
            if (conn != null) conn.rollback(); // Rollback if any error
            throw e;
        } finally {
            if (conn != null) conn.setAutoCommit(true);
            if (conn != null) conn.close();
        }
    }

    @Override
    public void update(Bill entity, String billId) throws SQLException {
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_BILL_SQL)) {
            stmt.setString(1, entity.getAccountNumber());
            stmt.setTimestamp(2, Timestamp.valueOf(entity.getBillDate()));
            stmt.setDouble(3, entity.getTotal());
            stmt.setString(4, billId);
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(String billId) throws SQLException {
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_BILL_SQL)) {
            stmt.setString(1, billId);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Bill> getAll() throws SQLException {
        Map<String, Bill.Builder> billMap = new HashMap<>();
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_BILLS_WITH_ITEMS_SQL)) {

            while (rs.next()) {
                String billId = rs.getString("bill_id");

                Bill.Builder billBuilder = billMap.get(billId);
                if (billBuilder == null) {
                    billBuilder = new Bill.Builder()
                            .billId(billId)
                            .accountNumber(rs.getString("accountNumber"))
                            .billDate(rs.getTimestamp("bill_date").toString())
                            .total(rs.getDouble("total"))
                            .items(new ArrayList<>());
                    billMap.put(billId, billBuilder);
                }

                String billItemId = rs.getString("bill_item_id");
                if (billItemId != null) { // bill might have zero items
                    BillItem item = new BillItem.Builder()
                            .billItemId(billItemId)
                            .billId(billId)
                            .itemId(rs.getString("item_id"))
                            .qty(rs.getInt("qty"))
                            .price(rs.getDouble("price"))
                            .build();
                    billBuilder.item(item);
                }
            }
        }
        // Convert builders to Bill objects
        List<Bill> bills = new ArrayList<>();
        for (Bill.Builder builder : billMap.values()) {
            bills.add(builder.build());
        }
        return bills;
    }

    @Override
    public Bill getById(String billId) throws SQLException {
        Bill.Builder billBuilder = null;
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BILL_WITH_ITEMS_BY_ID_SQL)) {
            stmt.setString(1, billId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    if (billBuilder == null) {
                        billBuilder = new Bill.Builder()
                                .billId(rs.getString("bill_id"))
                                .accountNumber(rs.getString("accountNumber"))
                                .billDate(rs.getTimestamp("bill_date").toString())
                                .total(rs.getDouble("total"))
                                .items(new ArrayList<>());
                    }

                    String billItemId = rs.getString("bill_item_id");
                    if (billItemId != null) {
                        BillItem item = new BillItem.Builder()
                                .billItemId(billItemId)
                                .billId(billId)
                                .itemId(rs.getString("item_id"))
                                .qty(rs.getInt("qty"))
                                .price(rs.getDouble("price"))
                                .build();
                        billBuilder.item(item);
                    }
                }
            }
        }
        return (billBuilder == null) ? null : billBuilder.build();
    }
}
