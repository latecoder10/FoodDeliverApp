package com.food.servlets;

import java.io.IOException;
import java.math.BigDecimal;

import com.food.DAO.RestaurantDAO;
import com.food.DAOImpl.RestaurantDAOImpl;
import com.food.modules.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registerRestaurant")
public class RegisterRestaurantServlet extends HttpServlet {

    private RestaurantDAO restaurantDAO = new RestaurantDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form parameters
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phone_number");
        BigDecimal rating;
        Integer estimateTimeArrival;

        // Validate input parameters
        try {
            rating = new BigDecimal(request.getParameter("rating"));
            estimateTimeArrival = Integer.parseInt(request.getParameter("estimate_time_arrival"));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid rating or estimated time arrival.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return; // Stop further processing
        }

        String cuisineType = request.getParameter("cuisine_type");
        boolean isActive = Boolean.parseBoolean(request.getParameter("is_active"));

        // Retrieve the admin user ID from the request
        String adminUserIdStr = request.getParameter("registerdAdminUID");
        if (adminUserIdStr == null || adminUserIdStr.isEmpty()) {
            response.sendRedirect("error.jsp");
            return;
        }

        Integer adminUserId;
        try {
            adminUserId = Integer.parseInt(adminUserIdStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid admin user ID.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return; // Stop further processing
        }

        // Create a Restaurant object
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setAddress(address);
        restaurant.setPhoneNumber(phoneNumber);
        restaurant.setRating(rating);
        restaurant.setCuisineType(cuisineType);
        restaurant.setActive(isActive);
        restaurant.setEstimateTimeArrival(estimateTimeArrival);
        restaurant.setAdminUserId(adminUserId);

        try {
            // Insert restaurant into the database
            boolean rowsUpdated = restaurantDAO.addRestaurant(restaurant);
            if (rowsUpdated) {
                // Redirect to a success page or back to the dashboard
                response.sendRedirect("adminDashboard.jsp?restaurantCreationSuccess=true");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error registering restaurant. Please try again.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
