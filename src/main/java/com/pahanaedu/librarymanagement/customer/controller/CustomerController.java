package com.pahanaedu.librarymanagement.customer.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "customerController", value = "/customer")
public class CustomerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if (id != null) {
            getById(req, resp, id);
        } else {
            getAll(req, resp);
        }
    }

    // get all customers
    private void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Get all customers");
    }

    // get customer by id
    private void getById(HttpServletRequest req, HttpServletResponse resp, String id) throws ServletException, IOException {
        resp.getWriter().write("Get customer by ID: " + id);
    }

    // save customer
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder jsonBody = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBody.append(line);
        }

        resp.setContentType("application/json");
        resp.getWriter().write(jsonBody.toString());
    }

    // update customer
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if (id != null) {
            resp.getWriter().write("Update customer by id: " + id);
        } else {
            resp.getWriter().write("Error: Customer ID is required for update");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    // delete customer
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if (id != null) {
            resp.getWriter().write("Delete customer by id: " + id);
        } else {
            resp.getWriter().write("Error: Customer ID is required for delete");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
