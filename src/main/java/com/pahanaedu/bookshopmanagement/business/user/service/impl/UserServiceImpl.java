package com.pahanaedu.bookshopmanagement.business.user.service.impl;

import com.pahanaedu.bookshopmanagement.persistence.userDAO.UserDAO;
import com.pahanaedu.bookshopmanagement.persistence.userDAO.impl.UserDAOImpl;
import com.pahanaedu.bookshopmanagement.business.user.dto.UserDTO;
import com.pahanaedu.bookshopmanagement.business.user.mapping.UserMapper;
import com.pahanaedu.bookshopmanagement.business.user.model.User;
import com.pahanaedu.bookshopmanagement.business.user.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private static final UserMapper userMapper = new UserMapper();

    public UserServiceImpl() {
        this.userDAO = new UserDAOImpl();
    }

    @Override
    public void save(UserDTO dto) throws SQLException {
        userDAO.save(userMapper.toEntity(dto));
    }

    @Override
    public void update(UserDTO dto, Integer id) throws SQLException {
        userDAO.update(userMapper.toEntity(dto), id);
    }

    @Override
    public void delete(Integer id) throws SQLException {
        userDAO.delete(id);
    }

    @Override
    public List<UserDTO> getAll() throws SQLException {
        return userMapper.toDTOList(userDAO.getAll());
    }

    @Override
    public UserDTO getById(Integer id) throws SQLException {
        return userMapper.toDTO(userDAO.getById(id));
    }

    @Override
    public UserDTO isUserExist(String email, String password) throws SQLException {
        User userExist = userDAO.isUserExist(email, password);
        if (userExist != null) {
            return userMapper.toDTO(userExist);
        }
        return null;
    }
}
