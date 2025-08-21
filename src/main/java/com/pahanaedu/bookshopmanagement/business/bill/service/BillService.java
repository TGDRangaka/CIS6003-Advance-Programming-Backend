package com.pahanaedu.bookshopmanagement.business.bill.service;

import com.pahanaedu.bookshopmanagement.business.bill.dto.BillDTO;

import java.sql.SQLException;
import java.util.List;

public interface BillService {
    void createBill(BillDTO bill) throws SQLException;
    void updateBill(BillDTO bill, String billId) throws SQLException;
    void deleteBill(String billId) throws SQLException;
    List<BillDTO> getAllBills() throws SQLException;
    BillDTO getBillById(String billId) throws SQLException;
}
