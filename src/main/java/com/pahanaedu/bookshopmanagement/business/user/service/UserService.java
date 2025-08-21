package com.pahanaedu.bookshopmanagement.business.user.service;

import com.pahanaedu.bookshopmanagement.business.user.dto.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    void save(UserDTO dto) throws SQLException;
    void update(UserDTO dto, Integer id) throws SQLException;
    void delete(Integer id) throws SQLException;
    List<UserDTO> getAll() throws SQLException;
    UserDTO getById(Integer id) throws SQLException;
    UserDTO isUserExist(String email, String password) throws SQLException;
}
