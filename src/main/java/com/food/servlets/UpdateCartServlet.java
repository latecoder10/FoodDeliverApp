package com.food.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateCart")
public class UpdateCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        System.out.println(cart);
        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }

        String removeId = request.getParameter("remove");
        if (removeId != null) {
            cart.remove(removeId);
        } else {
            Map<String, Integer> updatedQuantities = new HashMap<>();
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                if (paramName.startsWith("quantity_")) {
                    String menuId = paramName.substring("quantity_".length());
                    System.out.println(paramName.substring("quantity_".length()));
                    int quantity = Integer.parseInt(request.getParameter(paramName));
                    if (quantity > 0) {
                        updatedQuantities.put(menuId, quantity);
                    } else {
                        cart.remove(menuId);
                    }
                }
            }
            cart.putAll(updatedQuantities);
        }

        response.setContentType("application/json");
        PrintWriter ut = response.getWriter();
        String jsonResponse = "{\"success\": true}";
        ut.print(jsonResponse);
    }
}
