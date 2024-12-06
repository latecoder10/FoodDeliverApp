package com.food.servlets.restaurant;

import java.io.IOException;

import com.food.DAO.OrderDAO;
import com.food.DAOImpl.OrderDAOImpl;
import com.food.modules.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/chartDataServlet")
public class ChartDataServlet extends HttpServlet {
    private OrderDAO orderDAO;

    @Override
    public void init() {
        orderDAO = new OrderDAOImpl(); // Initialize your DAO implementation
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("restaurant") == null) {
            response.sendRedirect("login.jsp"); // Redirect if session is invalid
            return;
        }

        int restaurantId = ((Restaurant)session.getAttribute("restaurant")).getRestaurantId();
        String duration = request.getParameter("duration"); // Get the duration parameter

        // Fetch data based on the duration
        int ordersInProgress = orderDAO.getOrdersCountByStatus(restaurantId, "In Progress", duration);
        int ordersDelivered = orderDAO.getOrdersCountByStatus(restaurantId, "Delivered", duration);
        int ordersCancelled = orderDAO.getOrdersCountByStatus(restaurantId, "Cancelled", duration);
        int ordersPending = orderDAO.getOrdersCountByStatus(restaurantId, "Pending", duration);
        double totalOrderAmount = orderDAO.getTotalOrderAmount(restaurantId, duration);

        // Prepare the JSON response
        String jsonResponse = String.format(
            "{\"ordersInProgress\": %d, \"ordersDelivered\": %d, \"ordersCancelled\": %d, \"ordersPending\": %d, \"totalOrderAmount\": %.2f}",
            ordersInProgress, ordersDelivered, ordersCancelled, ordersPending, totalOrderAmount
        );

        // Set response type to JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }
}
