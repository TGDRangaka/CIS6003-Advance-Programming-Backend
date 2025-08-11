package com.pahanaedu.librarymanagement.bill.mapping;

import com.pahanaedu.librarymanagement.bill.dto.BillDTO;
import com.pahanaedu.librarymanagement.bill.dto.BillItemDTO;
import com.pahanaedu.librarymanagement.bill.model.Bill;
import com.pahanaedu.librarymanagement.bill.model.BillItem;

import java.util.List;
import java.util.stream.Collectors;

public class BillMapper {

    // Bill -> BillDTO
    public static BillDTO toDTO(Bill bill) {
        if (bill == null) return null;
        return new BillDTO(
                bill.getBillId(),
                bill.getAccountNumber(),
                bill.getBillDate(),
                bill.getTotal(),
                toBillItemDTOList(bill.getItems())
        );
    }

    // BillDTO -> Bill
    public static Bill toEntity(BillDTO dto) {
        if (dto == null) return null;
        return new Bill.Builder()
                .billId(dto.getBillId())
                .accountNumber(dto.getAccountNumber())
                .billDate(dto.getBillDate())
                .total(dto.getTotal())
                .items(toBillItemEntityList(dto.getItems()))
                .build();
    }

    // BillItem -> BillItemDTO
    public static BillItemDTO toBillItemDTO(BillItem item) {
        if (item == null) return null;
        return new BillItemDTO(
                item.getBillItemId(),
                item.getBillId(),
                item.getItemId(),
                item.getQty(),
                item.getPrice()
        );
    }

    // BillItemDTO -> BillItem
    public static BillItem toBillItemEntity(BillItemDTO dto) {
        if (dto == null) return null;
        return new BillItem.Builder()
                .billItemId(dto.getBillItemId())
                .billId(dto.getBillId())
                .itemId(dto.getItemId())
                .qty(dto.getQty())
                .price(dto.getPrice())
                .build();
    }

    // Helpers for List conversions
    public static List<BillItemDTO> toBillItemDTOList(List<BillItem> items) {
        if (items == null) return null;
        return items.stream()
                .map(BillMapper::toBillItemDTO)
                .collect(Collectors.toList());
    }

    public static List<BillItem> toBillItemEntityList(List<BillItemDTO> dtos) {
        if (dtos == null) return null;
        return dtos.stream()
                .map(BillMapper::toBillItemEntity)
                .collect(Collectors.toList());
    }
}
