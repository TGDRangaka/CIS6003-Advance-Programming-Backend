package com.pahanaedu.librarymanagement.bill.service.impl;

import com.pahanaedu.librarymanagement.bill.dao.BillDAO;
import com.pahanaedu.librarymanagement.bill.dao.impl.BillDAOImpl;
import com.pahanaedu.librarymanagement.bill.model.Bill;
import com.pahanaedu.librarymanagement.bill.service.BillService;

import java.sql.SQLException;
import java.util.List;

public class BillServiceImpl implements BillService {

    private final BillDAO billDAO;

    public BillServiceImpl() {
        this.billDAO = new BillDAOImpl();
    }

    @Override
    public void createBill(Bill bill) throws SQLException {
        billDAO.save(bill);
    }

    @Override
    public void updateBill(Bill bill, String billId) throws SQLException {
        billDAO.update(bill, billId);
    }

    @Override
    public void deleteBill(String billId) throws SQLException {
        billDAO.delete(billId);
    }

    @Override
    public List<Bill> getAllBills() throws SQLException {
        return billDAO.getAll();
    }

    @Override
    public Bill getBillById(String billId) throws SQLException {
        return billDAO.getById(billId);
    }
}
