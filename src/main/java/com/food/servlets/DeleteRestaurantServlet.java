package com.food.servlets;

import java.io.IOException;

import com.food.DAO.RestaurantDAO;
import com.food.DAOImpl.RestaurantDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteRestaurant")
public class DeleteRestaurantServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
        
        RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
        boolean isDeleted = restaurantDAO.deleteRestaurant(restaurantId);

        if (isDeleted) {
            // Redirect to a success page or back to the dashboard
            response.sendRedirect("adminDashboard.jsp?deleteSuccess=true");
        } else {
            // Redirect to an error page or back to the form with an error message
            response.sendRedirect("adminDashboard.jsp?deleteError=true");
        }
    }
}
