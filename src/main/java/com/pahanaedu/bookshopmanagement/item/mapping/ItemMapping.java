package com.pahanaedu.bookshopmanagement.item.mapping;

import com.pahanaedu.bookshopmanagement.item.dto.ItemDTO;
import com.pahanaedu.bookshopmanagement.item.model.Item;

public class ItemMapping {

    public static ItemDTO itemToDto(Item item) {
        if (item == null) {
            return null;
        }
        return new ItemDTO(
                item.getId(),
                item.getName(),
                item.getCategory(),
                item.getQty(),
                item.getPrice()
        );
    }

    public static Item dtoToItem(ItemDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Item.Builder()
                .id(dto.getId())
                .name(dto.getName())
                .category(dto.getCategory())
                .qty(dto.getQty())
                .price(dto.getPrice())
                .build();
    }
}
