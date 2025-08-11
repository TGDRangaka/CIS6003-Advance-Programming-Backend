package com.pahanaedu.bookshopmanagement.user.dao;

import com.pahanaedu.bookshopmanagement.user.model.User;
import com.pahanaedu.bookshopmanagement.util.SuperDAO;

import java.sql.SQLException;

public interface UserDAO extends SuperDAO<User, Integer> {
    boolean isUserExist(String email, String password) throws SQLException;
}
