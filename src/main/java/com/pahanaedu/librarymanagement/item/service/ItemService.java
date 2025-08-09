package com.pahanaedu.librarymanagement.item.service;

import com.pahanaedu.librarymanagement.item.dto.ItemDTO;

import java.sql.SQLException;
import java.util.List;

public interface ItemService {
    List<ItemDTO> getAll() throws SQLException;
    ItemDTO getById(Integer id) throws SQLException;
    void addItem(ItemDTO dto) throws SQLException;
    void updateItem(ItemDTO dto, int id) throws SQLException;
    void deleteItem(Integer id) throws SQLException;
}
