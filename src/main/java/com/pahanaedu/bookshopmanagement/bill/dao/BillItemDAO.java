package com.pahanaedu.bookshopmanagement.bill.dao;

import com.pahanaedu.bookshopmanagement.bill.model.BillItem;
import com.pahanaedu.bookshopmanagement.util.SuperDAO;

import java.sql.SQLException;
import java.util.List;

public interface BillItemDAO extends SuperDAO<BillItem, String> {
    List<BillItem> getBillItemsByBillId(String billId) throws SQLException;
}
