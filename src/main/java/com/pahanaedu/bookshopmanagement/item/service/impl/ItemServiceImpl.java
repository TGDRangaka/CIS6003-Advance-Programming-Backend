package com.pahanaedu.bookshopmanagement.item.service.impl;

import com.pahanaedu.bookshopmanagement.item.dao.ItemDAO;
import com.pahanaedu.bookshopmanagement.item.dao.impl.ItemDAOImpl;
import com.pahanaedu.bookshopmanagement.item.dto.ItemDTO;
import com.pahanaedu.bookshopmanagement.item.mapping.ItemMapping;
import com.pahanaedu.bookshopmanagement.item.model.Item;
import com.pahanaedu.bookshopmanagement.item.service.ItemService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    private final ItemDAO itemDAO = new ItemDAOImpl();

    @Override
    public List<ItemDTO> getAll() throws SQLException {
        List<Item> items = itemDAO.getAll();
        List<ItemDTO> dtoList = new ArrayList<>();
        for (Item item : items) {
            dtoList.add(ItemMapping.itemToDto(item));
        }
        return dtoList;
    }

    @Override
    public ItemDTO getById(Integer id) throws SQLException {
        Item item = itemDAO.getById(id);
        return item != null ? ItemMapping.itemToDto(item) : null;
    }

    @Override
    public void addItem(ItemDTO dto) throws SQLException {
        Item item = ItemMapping.dtoToItem(dto);
        itemDAO.save(item);
    }

    @Override
    public void updateItem(ItemDTO dto, int id) throws SQLException {
        Item item = ItemMapping.dtoToItem(dto);
        itemDAO.update(item, id);
    }

    @Override
    public void deleteItem(Integer id) throws SQLException {
        itemDAO.delete(id);
    }
}
