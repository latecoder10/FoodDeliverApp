package com.food.servlets.restaurant;


import java.io.IOException;
import java.io.PrintWriter;

import com.food.DAO.OrderDAO;
import com.food.DAOImpl.OrderDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateOrderStatus")
public class UpdateOrderStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO orderDAO = new OrderDAOImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String orderIdParam = request.getParameter("orderId");
	    String status = request.getParameter("status");
	    
	    if (orderIdParam != null && status != null) {
	        int orderId = Integer.parseInt(orderIdParam);

	        boolean success = orderDAO.updateOrderStatus(orderId, status); // Ensure this method is implemented correctly

	        response.setContentType("application/json");
	        PrintWriter out = response.getWriter();
	        if (success) {
	            out.print("{\"message\": \"Order status updated successfully\"}");
	        } else {
	            out.print("{\"message\": \"Failed to update order status\"}");
	        }
	        out.flush();
	    } else {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters");
	    }
	}

}
