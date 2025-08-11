package com.pahanaedu.librarymanagement.bill.service;

import com.pahanaedu.librarymanagement.bill.model.Bill;

import java.sql.SQLException;
import java.util.List;

public interface BillService {
    void createBill(Bill bill) throws SQLException;
    void updateBill(Bill bill, String billId) throws SQLException;
    void deleteBill(String billId) throws SQLException;
    List<Bill> getAllBills() throws SQLException;
    Bill getBillById(String billId) throws SQLException;
}
