package com.pahanaedu.librarymanagement.bill.dao;

import com.pahanaedu.librarymanagement.bill.model.BillItem;
import com.pahanaedu.librarymanagement.util.SuperDAO;

import java.sql.SQLException;
import java.util.List;

public interface BillItemDAO extends SuperDAO<BillItem, String> {
    List<BillItem> getBillItemsByBillId(String billId) throws SQLException;
}
