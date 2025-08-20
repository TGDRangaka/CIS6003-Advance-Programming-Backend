package com.pahanaedu.bookshopmanagement.business.bill.service.impl;

import com.pahanaedu.bookshopmanagement.persistence.billDAO.BillDAO;
import com.pahanaedu.bookshopmanagement.persistence.billDAO.impl.BillDAOImpl;
import com.pahanaedu.bookshopmanagement.business.bill.dto.BillDTO;
import com.pahanaedu.bookshopmanagement.business.bill.mapping.BillMapper;
import com.pahanaedu.bookshopmanagement.business.bill.model.Bill;
import com.pahanaedu.bookshopmanagement.business.bill.service.BillService;

import java.sql.SQLException;
import java.util.List;

public class BillServiceImpl implements BillService {

    private final BillDAO billDAO;

    public BillServiceImpl() {
        this.billDAO = new BillDAOImpl();
    }

    @Override
    public void createBill(BillDTO bill) throws SQLException {
        Bill entity = BillMapper.toEntity(bill);
        billDAO.save(entity);
    }

    @Override
    public void updateBill(BillDTO bill, String billId) throws SQLException {
        Bill entity = BillMapper.toEntity(bill);
        billDAO.update(entity, billId);
    }

    @Override
    public void deleteBill(String billId) throws SQLException {
        billDAO.delete(billId);
    }

    @Override
    public List<BillDTO> getAllBills() throws SQLException {
        List<Bill> all = billDAO.getAll();
        return BillMapper.toBillDTOList(all);
    }

    @Override
    public BillDTO getBillById(String billId) throws SQLException {
        Bill byId = billDAO.getById(billId);
        return BillMapper.toDTO(byId);
    }
}
