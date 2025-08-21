package com.pahanaedu.bookshopmanagement.business.bill.dto;

import java.util.List;

public class BillDTO {
    private String billId;
    private String accountNumber;
    private String billDate;
    private double total;
    private List<BillItemDTO> items;

    // No-args constructor
    public BillDTO() {
    }

    // All-args constructor
    public BillDTO(String billId, String accountNumber, String billDate, double total, List<BillItemDTO> items) {
        this.billId = billId;
        this.accountNumber = accountNumber;
        this.billDate = billDate;
        this.total = total;
        this.items = items;
    }

    // Getters and setters
    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<BillItemDTO> getItems() {
        return items;
    }

    public void setItems(List<BillItemDTO> items) {
        this.items = items;
    }
}
