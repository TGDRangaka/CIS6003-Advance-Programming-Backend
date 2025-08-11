package com.pahanaedu.bookshopmanagement.customer.controller;

import com.pahanaedu.bookshopmanagement.customer.dto.CustomerDTO;
import com.pahanaedu.bookshopmanagement.customer.mapping.CustomerMapping;
import com.pahanaedu.bookshopmanagement.customer.service.CustomerService;
import com.pahanaedu.bookshopmanagement.customer.service.impl.CustomerServiceImpl;
import com.pahanaedu.bookshopmanagement.util.UtilMatters;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "customerController", value = "/customer")
public class CustomerController extends HttpServlet {

    private final CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String accountNumber = req.getParameter("accountNumber");

            resp.setContentType("application/json");

            if (accountNumber != null) {
                CustomerDTO dto = customerService.getById(accountNumber);
                if (dto != null) {
                    resp.getWriter().write(CustomerMapping.toJson(dto));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("{\"error\": \"Customer not found\"}");
                }
            } else {
                List<CustomerDTO> customers = customerService.getAll();
                resp.getWriter().write(CustomerMapping.toJsonArray(customers));
            }
        } catch (Exception e) {
            UtilMatters.handleError(resp, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String json = UtilMatters.getJsonBody(req);
            CustomerDTO dto = CustomerMapping.toDto(json);
            customerService.addCustomer(dto);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("{\"message\": \"Customer created\"}");
        } catch (Exception e) {
            UtilMatters.handleError(resp, e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String accountNumber = req.getParameter("accountNumber");
            if (accountNumber == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\": \"accountNumber is required\"}");
                return;
            }

            String json = UtilMatters.getJsonBody(req);
            CustomerDTO dto = CustomerMapping.toDto(json);
            dto.setAccountNumber(accountNumber);
            customerService.updateCustomer(dto, accountNumber);

            resp.getWriter().write("{\"message\": \"Customer updated\"}");
        } catch (Exception e) {
            UtilMatters.handleError(resp, e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String accountNumber = req.getParameter("accountNumber");
            if (accountNumber == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\": \"accountNumber is required\"}");
                return;
            }

            customerService.deleteCustomer(accountNumber);
            resp.getWriter().write("{\"message\": \"Customer deleted\"}");
        } catch (Exception e) {
            UtilMatters.handleError(resp, e);
        }
    }
}
