package com.food.servlets;

import java.io.IOException;

import com.food.DAO.RestaurantDAO;
import com.food.DAOImpl.RestaurantDAOImpl;
import com.food.modules.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editRestaurant")
public class EditRestaurantServlet extends HttpServlet {

    private RestaurantDAO restaurantDAO = new RestaurantDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String restaurantIdParam = request.getParameter("restaurantId");

        if (restaurantIdParam != null) {
            try {
                int restaurantId = Integer.parseInt(restaurantIdParam);
                Restaurant restaurant = restaurantDAO.getRestaurantById(restaurantId);
                
                if (restaurant != null) {
                    request.setAttribute("restaurant", restaurant);
                    request.getRequestDispatcher("/editRestaurant.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Restaurant not found");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid restaurant ID");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Restaurant ID is required");
        }
    }
}
