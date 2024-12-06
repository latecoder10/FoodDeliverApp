package com.food.servlets.restaurant;

import java.io.IOException;
import java.util.List;

import com.food.DAO.OrderDAO;
import com.food.DAOImpl.OrderDAOImpl;
import com.food.modules.Order;
import com.food.modules.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/viewPendingOrders")
public class ViewPendingOrdersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Use getSession(false)

        if (session != null) {
            Restaurant restaurant = (Restaurant) session.getAttribute("restaurant"); // Get restaurant from session

            if (restaurant != null) {
                int restaurantId = restaurant.getRestaurantId(); // Get restaurant ID

                // Fetch pending orders for this restaurant
                List<Order> pendingOrders = orderDAO.getPendingOrdersByRestaurantId(restaurantId);
                request.setAttribute("pendingOrders", pendingOrders);
                request.getRequestDispatcher("dashboardServlet").include(request, response);
                
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Restaurant not found in session.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session expired or not found. Please log in again.");
        }
    }
}
