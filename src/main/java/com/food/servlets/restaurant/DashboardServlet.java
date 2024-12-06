package com.food.servlets.restaurant;

import java.io.IOException;

import com.food.DAO.OrderDAO;
import com.food.DAOImpl.OrderDAOImpl;
import com.food.modules.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dashboardServlet")
public class DashboardServlet extends HttpServlet {
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

        // Fetch today's data from the database
        int ordersInProgress = orderDAO.getOrdersCountByStatus(restaurantId, "In Progress", "today");
        int ordersDelivered = orderDAO.getOrdersCountByStatus(restaurantId, "Delivered", "today");
        int ordersCancelled = orderDAO.getOrdersCountByStatus(restaurantId, "Cancelled", "today");
        int ordersPending = orderDAO.getOrdersCountByStatus(restaurantId, "Pending", "today");
        int ordersReceived = ordersInProgress + ordersPending+ordersDelivered;
        double totalOrderAmount = orderDAO.getTotalOrderAmount(restaurantId, "today");
        System.out.println("chartData :" +ordersReceived+" "+ordersDelivered+" "+ordersCancelled+" "+ordersPending+" "+ordersInProgress+" "+totalOrderAmount );
        // Set attributes for JSP
        request.setAttribute("ordersReceived", ordersReceived);
        request.setAttribute("ordersDelivered", ordersDelivered);
        request.setAttribute("ordersCancelled", ordersCancelled);
        request.setAttribute("ordersPending", ordersPending);
        request.setAttribute("ordersInProgress", ordersInProgress);
        request.setAttribute("totalOrderAmount", totalOrderAmount);

        // Forward to JSP
        request.getRequestDispatcher("adminDashboard.jsp").include(request, response);
    }

}
