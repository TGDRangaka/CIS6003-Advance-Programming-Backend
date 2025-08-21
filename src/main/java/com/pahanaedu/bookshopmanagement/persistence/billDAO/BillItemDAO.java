package com.pahanaedu.bookshopmanagement.persistence.billDAO;

import com.pahanaedu.bookshopmanagement.business.bill.model.BillItem;
import com.pahanaedu.bookshopmanagement.persistence.SuperDAO;

import java.sql.SQLException;
import java.util.List;

public interface BillItemDAO extends SuperDAO<BillItem, String> {
    List<BillItem> getBillItemsByBillId(String billId) throws SQLException;
}
