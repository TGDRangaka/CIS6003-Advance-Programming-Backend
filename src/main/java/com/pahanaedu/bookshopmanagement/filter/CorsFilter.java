package com.pahanaedu.bookshopmanagement.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*") // apply to all routes
public class CorsFilter implements Filter {

    // Adjust this to match your frontend's origin
    private static final String FRONTEND_ORIGIN = "http://localhost:5173";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req  = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

//        print path
        System.out.println("CORS Filter: " + req.getMethod() + " " + req.getRequestURI());

        // Set CORS headers
        resp.setHeader("Access-Control-Allow-Origin", FRONTEND_ORIGIN);
        resp.setHeader("Vary", "Origin"); // good practice when varying by origin
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");
        // Set to true only if you need cookies/session across origins
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Max-Age", "3600");

        // Handle preflight
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204
            return;
        }

        chain.doFilter(request, response);
    }
}

