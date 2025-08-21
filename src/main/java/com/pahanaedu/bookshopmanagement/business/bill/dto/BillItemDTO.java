package com.pahanaedu.bookshopmanagement.business.bill.dto;

public class BillItemDTO {
    private String billItemId;
    private String billId;
    private String itemId;
    private int qty;
    private double price;

    // No-args constructor
    public BillItemDTO() {
    }

    // All-args constructor
    public BillItemDTO(String billItemId, String billId, String itemId, int qty, double price) {
        this.billItemId = billItemId;
        this.billId = billId;
        this.itemId = itemId;
        this.qty = qty;
        this.price = price;
    }

    // Getters and setters
    public String getBillItemId() {
        return billItemId;
    }

    public void setBillItemId(String billItemId) {
        this.billItemId = billItemId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
