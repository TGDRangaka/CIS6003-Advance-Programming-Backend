package com.pahanaedu.bookshopmanagement.business.user.controller;

import com.google.gson.Gson;
import com.pahanaedu.bookshopmanagement.business.user.dto.UserDTO;
import com.pahanaedu.bookshopmanagement.business.user.service.UserService;
import com.pahanaedu.bookshopmanagement.business.user.service.impl.UserServiceImpl;
import com.pahanaedu.bookshopmanagement.util.UtilMatters;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "userController", value = "/user/*")
public class UserController extends HttpServlet {
    private final UserService userService;

    public UserController() {
        this.userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String userId = req.getParameter("id");
            if (userId != null) {
                UserDTO user = userService.getById(Integer.parseInt(userId));
                resp.setContentType("application/json");
                resp.getWriter().write(new Gson().toJson(user));
            } else {
                List<UserDTO> users = userService.getAll();
                resp.setContentType("application/json");
                resp.getWriter().write(new Gson().toJson(users));
            }
        } catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String json = UtilMatters.getJsonBody(req);
            UserDTO userDTO = new Gson().fromJson(json, UserDTO.class);

            userService.save(userDTO);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("{\"message\": \"User created successfully\"}");
        } catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String json = UtilMatters.getJsonBody(req);
            UserDTO userDTO = new Gson().fromJson(json, UserDTO.class);
            int userId = Integer.parseInt(req.getParameter("id"));

            userService.update(userDTO, userId);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"message\": \"User updated successfully\"}");
        } catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(req.getParameter("id"));
            userService.delete(userId);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"message\": \"User deleted successfully\"}");
        } catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
