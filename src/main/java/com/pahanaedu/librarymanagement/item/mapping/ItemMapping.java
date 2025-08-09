package com.pahanaedu.librarymanagement.item.mapping;

import com.pahanaedu.librarymanagement.item.dto.ItemDTO;
import com.pahanaedu.librarymanagement.item.model.Item;

public class ItemMapping {

    public static ItemDTO itemToDto(Item item) {
        if (item == null) {
            return null;
        }
        return new ItemDTO(
                item.getId(),
                item.getName(),
                item.getAuthor(),
                item.getCategory(),
                item.getAvailableCopies()
        );
    }

    public static Item dtoToItem(ItemDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Item.Builder()
                .id(dto.getId())
                .name(dto.getName())
                .author(dto.getAuthor())
                .category(dto.getCategory())
                .availableCopies(dto.getAvailableCopies())
                .build();
    }
}
