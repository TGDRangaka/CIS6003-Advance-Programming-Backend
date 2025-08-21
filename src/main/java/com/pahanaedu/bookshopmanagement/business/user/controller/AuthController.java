package com.pahanaedu.bookshopmanagement.business.user.controller;

import com.google.gson.Gson;
import com.pahanaedu.bookshopmanagement.business.user.dto.UserDTO;
import com.pahanaedu.bookshopmanagement.business.user.service.UserService;
import com.pahanaedu.bookshopmanagement.business.user.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "authController", value = "/auth/*")
public class AuthController extends HttpServlet {
    private final UserService userService;

    public AuthController() {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && pathInfo.equals("/login")) {
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            try {
                UserDTO userExist = userService.isUserExist(email, password);
                if (userExist != null) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().write(new Gson().toJson(userExist));
                } else {
                    resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    resp.getWriter().write("{\"error\": \"Invalid email or password\"}");
                }
            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("{\"error\": \"Endpoint not found\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && pathInfo.equals("/register")) {
            Gson gson = new Gson();
            UserDTO userDTO = gson.fromJson(req.getReader(), UserDTO.class);

            try {
                userService.save(userDTO);
                resp.getWriter().write("{\"message\": \"Registration successful\"}");
            } catch (SQLException e){
                // check is duplicate email
                if (e.getMessage().contains("Duplicate entry")) {
                    resp.setStatus(HttpServletResponse.SC_CONFLICT);
                    resp.getWriter().write("{\"error\": \"Email already exists\"}");
                } else {
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    resp.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
                }
            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("{\"error\": \"Endpoint not found\"}");
        }
    }
}
