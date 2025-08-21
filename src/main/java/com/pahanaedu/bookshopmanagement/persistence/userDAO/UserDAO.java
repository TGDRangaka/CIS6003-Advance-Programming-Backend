package com.pahanaedu.bookshopmanagement.persistence.userDAO;

import com.pahanaedu.bookshopmanagement.business.user.model.User;
import com.pahanaedu.bookshopmanagement.persistence.SuperDAO;

import java.sql.SQLException;

public interface UserDAO extends SuperDAO<User, Integer> {
    User isUserExist(String email, String password) throws SQLException;
}
