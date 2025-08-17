package com.pahanaedu.bookshopmanagement.user.dao.impl;

import com.pahanaedu.bookshopmanagement.config.DatabaseConfig;
import com.pahanaedu.bookshopmanagement.user.dao.UserDAO;
import com.pahanaedu.bookshopmanagement.user.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static final String INSERT_SQL = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE users SET name = ?, email = ?, password = ? WHERE user_id = ?";
    private static final String DELETE_SQL = "DELETE FROM users WHERE user_id = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM users";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM users WHERE user_id = ?";
    private static final String CHECK_USER_SQL = "SELECT * FROM users WHERE email = ? AND password = ?";

    @Override
    public void save(User entity) throws SQLException {
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getEmail());
            ps.setString(3, entity.getPassword());
            ps.executeUpdate();
        }
    }

    @Override
    public void update(User entity, Integer id) throws SQLException {
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getEmail());
            ps.setString(3, entity.getPassword());
            ps.setInt(4, id);
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL)) {

            while (rs.next()) {
                users.add(User.builder()
                        .userId(rs.getInt("user_id"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .build());
            }
        }
        return users;
    }

    @Override
    public User getById(Integer id) throws SQLException {
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return User.builder()
                        .userId(rs.getInt("user_id"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .build();
            }
        }
        return null;
    }

    @Override
    public User isUserExist(String email, String password) throws SQLException {
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(CHECK_USER_SQL)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return User.builder()
                        .userId(rs.getInt("user_id"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .build();
            }
        }
        return null;
    }
}
