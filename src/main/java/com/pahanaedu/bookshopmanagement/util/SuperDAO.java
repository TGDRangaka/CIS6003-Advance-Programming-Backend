package com.pahanaedu.bookshopmanagement.util;

import java.sql.SQLException;
import java.util.List;

public interface SuperDAO<T,ID> {
    void save(T entity) throws SQLException;
    void update(T entity, ID id) throws SQLException;
    void delete(ID id) throws SQLException;
    List<T> getAll() throws SQLException;
    T getById(ID id) throws SQLException;
}
