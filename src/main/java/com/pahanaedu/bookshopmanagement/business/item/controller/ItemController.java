package com.pahanaedu.bookshopmanagement.business.item.controller;

import com.google.gson.Gson;
import com.pahanaedu.bookshopmanagement.business.item.dto.ItemDTO;
import com.pahanaedu.bookshopmanagement.business.item.service.ItemService;
import com.pahanaedu.bookshopmanagement.business.item.service.impl.ItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "itemController", value = "/item")
public class ItemController extends HttpServlet {

    private final ItemService itemService = new ItemServiceImpl();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        resp.setContentType("application/json");

        try {
            if (id != null) {
                ItemDTO item = itemService.getById(Integer.valueOf(id));
                if (item != null) {
                    resp.getWriter().write(gson.toJson(item));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("{\"error\": \"Item not found\"}");
                }
            } else {
                List<ItemDTO> items = itemService.getAll();
                resp.getWriter().write(gson.toJson(items));
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ItemDTO dto = getJsonBody(req);
            itemService.addItem(dto);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("{\"message\": \"Item created successfully\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Item ID is required\"}");
            return;
        }

        try {
            ItemDTO dto = getJsonBody(req);
            itemService.updateItem(dto, Integer.valueOf(id));
            resp.getWriter().write("{\"message\": \"Item updated successfully\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Item ID is required\"}");
            return;
        }

        try {
            itemService.deleteItem(Integer.valueOf(id));
            resp.getWriter().write("{\"message\": \"Item deleted successfully\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    private ItemDTO getJsonBody(HttpServletRequest req) throws IOException {
        StringBuilder jsonBody = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBody.append(line);
        }
        return gson.fromJson(jsonBody.toString(), ItemDTO.class);
    }
}
