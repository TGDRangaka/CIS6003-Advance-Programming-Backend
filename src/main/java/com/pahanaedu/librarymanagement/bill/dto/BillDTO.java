package com.pahanaedu.librarymanagement.bill.dto;

import java.time.LocalDateTime;
import java.util.List;

public class BillDTO {
    private String billId;
    private String accountNumber;
    private LocalDateTime billDate;
    private double total;
    private List<BillItemDTO> items;

    // No-args constructor
    public BillDTO() {
    }

    // All-args constructor
    public BillDTO(String billId, String accountNumber, LocalDateTime billDate, double total, List<BillItemDTO> items) {
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

    public LocalDateTime getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDateTime billDate) {
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
